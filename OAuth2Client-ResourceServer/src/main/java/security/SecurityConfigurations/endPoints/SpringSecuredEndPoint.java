package security.SecurityConfigurations.endPoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.Arrays;
import java.util.Base64;

@RestController
@RequestMapping("/user")
public class SpringSecuredEndPoint {



    @GetMapping("/me")
    @PreAuthorize("#oauth2.hasAnyScope('read')")
    public ResponseEntity<String> endpoint1(OAuth2Authentication auth2Authentication){

        Object details = auth2Authentication.getDetails();
        if (details instanceof OAuth2AuthenticationDetails) {
            OAuth2AuthenticationDetails oauthsDetails = (OAuth2AuthenticationDetails) details;
            String token = oauthsDetails.getTokenValue();
            //context.setAccessToken(new DefaultOAuth2AccessToken(token));
            //return ResponseEntity.ok(((OAuth2AuthenticationDetails)auth2Authentication.getDetails()).getTokenValue());
            return ResponseEntity.ok(auth2Authentication.getUserAuthentication().getPrincipal().toString());
        }

        return null;
    }

    @GetMapping("/test")
    public ResponseEntity<String> endpoint2(){

        return ResponseEntity.ok("test");
    }

}
