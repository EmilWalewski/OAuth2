package security.SecurityConfigurations.oauthServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

public class CustomTokenService implements AuthorizationServerTokenServices {

    @Autowired
    DefaultTokenServices defaultTokenServices;

    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication oAuth2Authentication) throws AuthenticationException {

        OAuth2AccessToken token = defaultTokenServices.createAccessToken(oAuth2Authentication);
        JwtTokenStore store = new JwtTokenStore(new JwtAccessTokenConverter());
        OAuth2AccessToken newToken = jwtAccessTokenConverter.enhance(token, oAuth2Authentication);

        return token;
    }

    @Override
    public OAuth2AccessToken refreshAccessToken(String s, TokenRequest tokenRequest) throws AuthenticationException {
        return null;
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication oAuth2Authentication) {
        return null;
    }
}
