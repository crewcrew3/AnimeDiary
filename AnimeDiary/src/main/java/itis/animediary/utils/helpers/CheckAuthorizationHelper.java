package itis.animediary.utils.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CheckAuthorizationHelper {

    public static boolean isAuthorized () {
        // Проверка на наличие аутентифицированного пользователя в SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !(authentication instanceof org.springframework.security.authentication.AnonymousAuthenticationToken); // Пользователь аутентифицирован
    }
}
