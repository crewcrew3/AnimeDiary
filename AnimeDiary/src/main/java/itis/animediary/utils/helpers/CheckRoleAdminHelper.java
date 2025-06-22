package itis.animediary.utils.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CheckRoleAdminHelper {

    public static boolean isAdmin() {
        boolean isAdmin = false;
        if (CheckAuthorizationHelper.isAuthorized()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        }
        return isAdmin;
    }
}
