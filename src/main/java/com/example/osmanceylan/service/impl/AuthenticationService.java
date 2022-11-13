package com.example.osmanceylan.service.impl;

import com.example.osmanceylan.dto.UserDto;
import com.example.osmanceylan.security.UserPrincipal;
import com.example.osmanceylan.security.jwt.IJwtProvider;
import com.example.osmanceylan.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthenticationService implements IAuthenticationService {

    //injection
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IJwtProvider jwtProvider;
    @Override
    public String login(UserDto userDto) {
        //
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword()));
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return jwtProvider.generateToken(userPrincipal);
    }
}
