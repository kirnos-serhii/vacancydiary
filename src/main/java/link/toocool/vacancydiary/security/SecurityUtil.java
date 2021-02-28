package link.toocool.vacancydiary.security;

import link.toocool.vacancydiary.entity.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Provides utility security methods.
 *
 * @author Serhii Kirnos
 */
@Component("securityUtil")
public class SecurityUtil {

    /**
     * Check is user has access to get or edit user by id
     * @param userId user's id
     * @return <code>true</code> if authenticated user has access to this uses
     */
    public boolean hasAccessUser(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().containsAll(Role.USER.getAuthorities())) {
            return SecurityUtil.getSecurityUserId().equals(userId);
        }
        return authentication.getAuthorities().containsAll(Role.ADMIN.getAuthorities());
    }

    public static SecurityUser getSecurityUser() {
        return ((SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
    }

    public static Long getSecurityUserId() {
        return getSecurityUser().getId();
    }
}
