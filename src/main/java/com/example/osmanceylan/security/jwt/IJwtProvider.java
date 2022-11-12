package com.example.osmanceylan.security.jwt;

import com.example.osmanceylan.security.UserPrincipal;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyFactory;

public interface IJwtProvider {
    //
    KeyFactory getKeyFactory();
    // 1st method(Token Create)
    String generateToken(UserPrincipal userPrincipal);
    // 2nd method (jwt seperation)
    String resolveToken(HttpServletRequest httpServletRequest);
    Authentication getAuthentication(HttpServletRequest httpServletRequest);

    // 3rd Method (Token time control)
    boolean isValidToken(HttpServletRequest httpServletRequest);
}
