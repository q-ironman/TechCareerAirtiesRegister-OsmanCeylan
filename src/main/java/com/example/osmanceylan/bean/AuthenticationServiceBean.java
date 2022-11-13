package com.example.osmanceylan.bean;

import com.example.osmanceylan.service.impl.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationServiceBean {
    @Bean
    public AuthenticationService authenticationServiceBeanMethod(){
        return new AuthenticationService();
    }
}
