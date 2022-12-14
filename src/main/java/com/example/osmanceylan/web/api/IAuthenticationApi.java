package com.example.osmanceylan.web.api;

import com.example.osmanceylan.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthenticationApi {
    ResponseEntity<?> login(@RequestBody UserDto dto);
    ResponseEntity<?> register(@RequestBody UserDto dto);
}
