package security.SecurityConfigurations.endPoints;

import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomAuthorizationCodeService extends InMemoryAuthorizationCodeServices implements AuthorizationCodeServices {


    private Random randomChar;

    //private static Map<String, OAuth2Authentication> oAuth2AuthenticationMap = new ConcurrentHashMap<>();


    @Override
    public String createAuthorizationCode(OAuth2Authentication oAuth2Authentication) {


        randomChar = new Random();

        int digit;
        int letter;

        String code = "";
        int rand;

        for (int i = 0; i < 6; i++){
            rand = (int)(Math.random() * 10) + 1;

            if (rand > 5){

                letter = randomChar.nextInt(122 - 97 + 1) + 97;
                code += (char) letter;
            }
            else{

                digit = randomChar.nextInt(9 - 0);
                code += digit;
            }

        }

        //oAuth2AuthenticationMap.put(code, oAuth2Authentication);
        store(code, oAuth2Authentication);
        return code;
    }

    @Override
    public OAuth2Authentication consumeAuthorizationCode(String s) throws InvalidGrantException {

        //if (oAuth2AuthenticationMap.keySet().contains(s)){

           // OAuth2Authentication auth = oAuth2AuthenticationMap.get(s);
            //oAuth2AuthenticationMap.remove(s);

            return remove(s);
        //}

        //return null;
    }
}
