package link.toocool.vacancydiary.security;

import link.toocool.vacancydiary.entity.Status;
import link.toocool.vacancydiary.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private final String username;

    private final String password;

    private final Long id;

    private final Set<SimpleGrantedAuthority> authorities;

    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(User user) {
        return new SecurityUser(
                user.getEmail(), user.getPassword(), user.getId(),
                user.getRole().getAuthorities(),
                user.getStatus().equals(Status.ACTIVE)
        );
    }
}