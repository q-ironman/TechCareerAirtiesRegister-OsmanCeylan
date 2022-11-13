package com.example.osmanceylan.service;

import com.example.osmanceylan.dto.UserDto;

public interface IAuthenticationService {
    String login(UserDto userDto);
}
