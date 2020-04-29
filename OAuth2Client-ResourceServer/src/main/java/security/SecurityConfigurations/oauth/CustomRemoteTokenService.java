package security.SecurityConfigurations.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

public class CustomRemoteTokenService implements ResourceServerTokenServices {

    @Value("${spring.ouath2.client_id}")
    private String client_id;

    @Value("${spring.ouath2.client_secret}")
    private String client_secret;

    private RestTemplate restTemplate;
    private AccessTokenConverter accessTokenConverter;


    public CustomRemoteTokenService() {
        restTemplate = new RestTemplate();
        accessTokenConverter = new DefaultAccessTokenConverter();
    }

    @Override
    public OAuth2Authentication loadAuthentication(String s) throws AuthenticationException, InvalidTokenException {

        HttpHeaders headers = new HttpHeaders();

        Map<String, Object> map = getAuthentication("http://localhost:8080/oauth/check_token?token=" + s, headers);

        if (map == null || map.isEmpty() || map.get("error") != null) {
            throw new InvalidTokenException("Token not allowed");
        }

        //OAuth2Authentication auth = accessTokenConverter.extractAuthentication(map);
        return accessTokenConverter.extractAuthentication(map);
}

    @Override
    public OAuth2AccessToken readAccessToken(String s) {
        return null;
    }

    private Map<String, Object> getAuthentication(String path, HttpHeaders headers){

        if (headers.getContentType() == null)
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic "+ new String(Base64.getEncoder().encode((client_id+":"+client_secret).getBytes())));

        //HttpEntity httpEntity = new HttpEntity(headers);

        Map map = restTemplate.exchange(path, HttpMethod.POST, new HttpEntity<MultiValueMap<String, String>>(null, headers), Map.class).getBody();

        Map<String, Object> result = map;

        return result;
    }
}
