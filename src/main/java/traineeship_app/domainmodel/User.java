package traineeship_app.domainmodel;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;  // required for Spring Security session management
import java.util.Collection;
import java.util.List;

public class User implements UserDetails, Serializable {

    private Long id;  // ID for database storage

    private String username;
    private String password;

    private Role role;

    // Default constructor - required for JPA
    public User() {}

    // User constructor
    public User(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean isAccountNonExpired() {    // Checks if the account has expired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {    // Checks if the account is locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {    // Checks if credentials (password) are expired
        return true;
    }

    @Override
    public boolean isEnabled() {    // Checks if the user is active
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {    // User roles/permissions
        return List.of((GrantedAuthority) () -> "ROLE_" + role.name());
    }

}
