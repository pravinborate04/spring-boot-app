package com.borate.pravin.pim.config;

import com.borate.pravin.pim.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Pravin Borate
 * 12/03/21
 */
public class AuthUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private boolean active;

    private Long id;

    private User user;

    private List<GrantedAuthority> authorities;

    public AuthUserDetails(User user, List<String> grantedAuthority) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.id = user.getId();
        this.setActive(user.isActive());
        this.user = user;
        this.authorities = grantedAuthority.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * return User
     */
    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "AuthUserDetails [username=" + username + ", password=" + password + ", active=" + active + ", id=" + id + ", authorities=" + authorities + "]";
    }

}

