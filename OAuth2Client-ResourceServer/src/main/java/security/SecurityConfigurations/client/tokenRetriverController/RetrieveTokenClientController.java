package security.SecurityConfigurations.client.tokenRetriverController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import security.SecurityConfigurations.client.cookieProvider.CookieProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Base64;

@RestController
@RequestMapping("/api")
@PropertySource("classpath:application.properties")
public class RetrieveTokenClientController {

    @Value("${spring.ouath2.client_id}")
    private String client_id;

    @Value("${spring.ouath2.client_secret}")
    private String client_secret;

    @Value("${spring.oauth2.redirect_url}")
    private String redirect_url;

    RestTemplate restTemplate;


    @GetMapping(value = "/generateToken")
    public String code(@RequestParam("code") String authCode, HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException {

        String client_credentials = client_id+":"+client_secret;
        String credentials = new String(Base64.getEncoder().encode(client_credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic "+credentials);

        HttpEntity request = new HttpEntity(headers);

        restTemplate = new RestTemplate();

        String accessToken = "http://localhost:8080/oauth/token?code="+authCode+"&grant_type=authorization_code"
                +"&redirect_uri=http://localhost:8090/api/generateToken";

        ResponseEntity<String> response = restTemplate.exchange(accessToken, HttpMethod.POST, request, String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(response.getBody());

        String token = jsonNode.path("access_token").asText();

        CookieProvider.generateCookie(res, token, Integer.parseInt(jsonNode.path("expires_in").asText()));

        try {

            res.sendRedirect(redirect_url);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
