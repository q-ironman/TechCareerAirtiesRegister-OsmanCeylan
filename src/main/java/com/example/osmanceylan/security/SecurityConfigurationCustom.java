package com.example.osmanceylan.security;

import com.example.osmanceylan.bean.PasswordEncoderBean;
import com.example.osmanceylan.service.impl.UserDetailsServiceCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//lombok
@RequiredArgsConstructor

@EnableWebSecurity
@Configuration
public class SecurityConfigurationCustom extends WebSecurityConfigurerAdapter {

    // field
    private final PasswordEncoderBean passwordEncoderBean;
    private final UserDetailsServiceCustom serviceCustom;

    // Bean
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")//localhost
                        .allowedMethods("*");//Get,Post etc work
            }
        };
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //authontication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(serviceCustom).passwordEncoder(passwordEncoderBean.passwordEncoderMethod());
    }
    //authorization and getting the users role
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Cross Side Request Forgery
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/api/authentication/**").permitAll().anyRequest().authenticated();
    }

    // Web Security
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
