package com.example.osmanceylan.web.api.impl;

import com.example.osmanceylan.dto.UserDto;
import com.example.osmanceylan.service.IAuthenticationService;
import com.example.osmanceylan.service.IUserService;
import com.example.osmanceylan.web.api.IAuthenticationApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationApi implements IAuthenticationApi {
    private final IAuthenticationService authenticationService;
    private final IUserService userService;
    //Login
    // http://localhost:6060//api/authentication/login
    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto dto) {
        return new ResponseEntity<>(authenticationService.login(dto), HttpStatus.OK);
    }
    //Register
    // http://localhost:6060/api/authentication/register
    @Override
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto dto) {
        //username unique
        if (userService.findByUsername(dto.getUsername()).isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.create(dto),HttpStatus.CREATED);
    }
}
