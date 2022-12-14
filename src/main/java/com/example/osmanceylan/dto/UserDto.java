package com.example.osmanceylan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;

//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2


public class UserDto {


    private Long id;
    private String username;
    private String name;
    private String password;
    private String authorities;
    private Date createdDate;
}
