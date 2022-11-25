package com.example.osmanceylan.security;

import com.example.osmanceylan.entity.User;
import com.example.osmanceylan.roles.ERoles;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//lombok
@Data
@NoArgsConstructor
@Log4j2


public class UserPrincipal implements UserDetails {

    //fields
    private Long id;
    private String username;
    transient private String password;

    //private User user;
    private List<SimpleGrantedAuthority> authorities;
    public UserPrincipal(Long id, String username, String password, List<SimpleGrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
        //Collections.singletonList(new SimpleGrantedAuthority(ERoles.ADMIN.toString()));
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
}
