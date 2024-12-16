package com.todolist.bff_todolist.domain.model.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserAccount implements UserDetails {

    private String username;
    private String password;
    private Instant accountDueTime;
    private boolean accountNonLocked;
    private Instant credentialsDueTime;
    private boolean enabled;
    private List<Role> roles;
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.name())).toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountDueTime.isAfter(Instant.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsDueTime.isAfter(Instant.now());
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
