package com.example.osmanceylan.service;

import com.example.osmanceylan.dto.UserDto;
import com.example.osmanceylan.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUserService {
    UserDto entity2Dto(User entity);
    User dto2Entity(UserDto dto);

    // Create
    UserDto create(UserDto dto);
    // List
    List<UserDto> list();
    // Find
    UserDto find(Long id) ;
    // Find by Username
    Optional<User> findByUsername(String username);
    // Update
    UserDto update(Long id, UserDto userDto) ;
    // Delete
    Map<String,Boolean> delete(Long id) ;
}
