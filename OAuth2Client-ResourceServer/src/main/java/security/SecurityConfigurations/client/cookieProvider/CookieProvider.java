package security.SecurityConfigurations.client.cookieProvider;

import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieProvider{

    public static void generateCookie(HttpServletResponse response, String token, int maxAge){

        Cookie cookie = new Cookie("Authorization", token);
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        cookie.setDomain("localhost");
        cookie.setPath("/");

        response.addCookie(cookie);
    }


    public static void clearCookie(HttpServletResponse response){

        Cookie cookie = new Cookie("Authorization", null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);

        response.addCookie(cookie);
    }

    public static String retrieveToken(HttpServletRequest request){

        return WebUtils.getCookie(request, "Authorization") != null ?
                WebUtils.getCookie(request, "Authorization").getValue() : null;

    }
}
