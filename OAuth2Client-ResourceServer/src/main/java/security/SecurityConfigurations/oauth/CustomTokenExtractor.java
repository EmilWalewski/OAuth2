package security.SecurityConfigurations.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import security.SecurityConfigurations.client.cookieProvider.CookieProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class CustomTokenExtractor implements TokenExtractor {

    @Override
    public Authentication extract(HttpServletRequest request) {

        //Enumeration<String> headers = request.getHeaders("Authorization");

        String token2 = CookieProvider.retrieveToken(request);

        String add1 = request.getRemoteAddr();
        StringBuffer add2 = request.getRequestURL();
        String add3 = request.getRequestURI();


        if (token2 != null) {
            request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, "Bearer");
            return new PreAuthenticatedAuthenticationToken(token2, "");
        }

//        while (headers.hasMoreElements()) { // typically there is only one (most servers enforce that)
//
//            String value = headers.nextElement();
//
//            if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
//
//                String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
//                // Add this here for the auth details later. Would be better to change the
//                // signature of this method.
//                request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, value.substring(0, OAuth2AccessToken.BEARER_TYPE.length()).trim());
//
//                int commaIndex = authHeaderValue.indexOf(',');
//
//                if (commaIndex > 0) {
//                    authHeaderValue = authHeaderValue.substring(0, commaIndex);
//                }
//                return new PreAuthenticatedAuthenticationToken(authHeaderValue, "");
//            }
//        }

        return null;
    }
}
