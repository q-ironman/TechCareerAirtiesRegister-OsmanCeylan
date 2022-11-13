package com.example.osmanceylan.service.impl;

import com.example.osmanceylan.dto.UserDto;
import com.example.osmanceylan.entity.User;
import com.example.osmanceylan.exception.UserNotFoundException;
import com.example.osmanceylan.security.UserPrincipal;
import com.example.osmanceylan.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//lombok
@RequiredArgsConstructor


@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    // Injection
    private final IUserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        User entity = service.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
        return new UserPrincipal(entity.getId(), entity.getUsername(), entity.getPassword());
    }
}
