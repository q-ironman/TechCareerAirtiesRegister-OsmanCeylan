package com.example.osmanceylan.service.impl;

import com.example.osmanceylan.bean.ModelMapperBean;
import com.example.osmanceylan.bean.PasswordEncoderBean;
import com.example.osmanceylan.dto.UserDto;
import com.example.osmanceylan.entity.User;
import com.example.osmanceylan.exception.UserNotFoundException;
import com.example.osmanceylan.repository.IUserRepository;
import com.example.osmanceylan.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

//lombok
@RequiredArgsConstructor
@Log4j2

@Service
public class UserService implements IUserService {

    // Injection
    private final ModelMapperBean modelMapperBean;
    private final IUserRepository repository;
    private final PasswordEncoderBean passwordEncoderBean;

    @Override
    public UserDto entity2Dto(User entity) {
        return modelMapperBean.modelMapperMethod().map(entity, UserDto.class);
    }

    @Override
    public User dto2Entity(UserDto dto) {
        return modelMapperBean.modelMapperMethod().map(dto, User.class);
    }

    @Override
    public UserDto create(UserDto dto) {
        if(dto != null){
            dto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(dto.getPassword()));
            User user = dto2Entity(dto);
            repository.save(user);
        }
        return dto;
    }

    @Override
    public List<UserDto> list() {
        Iterable<User> lst = repository.findAll();
        List<UserDto> res = new ArrayList<>();
        lst.forEach(tmp -> res.add(entity2Dto(tmp)));
        return res;
    }

    @Override
    public UserDto find(Long id) {
        User res = repository.findById(id).orElseThrow(()-> new UserNotFoundException());
        return entity2Dto(res);
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        User res = repository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
        return Optional.ofNullable(entity2Dto(res));


    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        User res =  repository.findById(id).orElseThrow(()-> new UserNotFoundException());
        res.setName(userDto.getName());
        res.setUsername(userDto.getUsername());
        res.setPassword(userDto.getPassword());
        repository.save(res);
        return userDto;
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        User res =  repository.findById(id).orElseThrow(()-> new UserNotFoundException());
        repository.delete(res);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",true);
        return response;
    }
}
