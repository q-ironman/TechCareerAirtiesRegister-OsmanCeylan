package com.example.osmanceylan.security;

import com.example.osmanceylan.bean.PasswordEncoderBean;
import com.example.osmanceylan.roles.ERoles;
import com.example.osmanceylan.roles.Roles;
import com.example.osmanceylan.security.jwt.JwtAuthorizationFilter;
import com.example.osmanceylan.service.impl.UserDetailsServiceCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//lombok
@RequiredArgsConstructor

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true

)
public class SecurityConfigurationCustom extends WebSecurityConfigurerAdapter {

    //field
    private final PasswordEncoderBean passwordEncoderBean;
    private final UserDetailsServiceCustom customUserDetailsService;


    //////////////////////////////////////////////////////////////////////////////////
    //BEAN
    //import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")
                        .allowedMethods("*"); //POST,GET,
            }
        };
    }

    // JwtAuthorizationFilter için @Autowired için bean olarak işaretledim
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilterBeanMethod(){
        return new JwtAuthorizationFilter();
    }

    // AUTHENTICATION_MANAGER
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //////////////////////////////////////////////////////////////////////////////////
    //kimlik doğrulama
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoderBean.passwordEncoderMethod());
    }

    //yetkilendirme yapılandırılması roller sadece ilgili kişiler erişsin
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Cross  Side Request Forgery (session kullanmayacaksak kapataabiliriz) ve JWT kullancağız.
        http.csrf().disable();

        // session kullanmayacaksak kapatabiliriz)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //login
        http.authorizeRequests()
                .antMatchers("/api/authentication/**").permitAll() //login and register
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();

        //authentication JWT için yazıldı
        //filterdan önce
        http.addFilterBefore(jwtAuthorizationFilterBeanMethod(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/h2-console/**","/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/assets/**","/fonts/**","/dis/**","/vendor1/**","/assets2/**");
    } //end configure WebSecurity(1)
}
