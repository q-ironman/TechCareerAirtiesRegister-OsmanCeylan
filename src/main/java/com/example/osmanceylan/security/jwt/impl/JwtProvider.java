package com.example.osmanceylan.security.jwt.impl;

import com.example.osmanceylan.security.UserPrincipal;
import com.example.osmanceylan.security.jwt.IJwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

// Spring introduction
@Component
public class JwtProvider implements IJwtProvider {
    private static final String JWT_TOKEN_PREFIX = "Bearer";
    private static final String JWT_HEADER_STRING = "Authorization";

    //application.properties
    @Value("${authentication.jwt.expiration-ms}")
    private Long JWT_EXPIRATION_MS;
    // Public Key
    @Value("${authentication.jwt.public-key}")
    private PublicKey jwtPublicKey;
    // Private Key
    @Value("${authentication.jwt.private-key}")
    private PrivateKey jwtPrivateKey;
    @Override
    public KeyFactory getKeyFactory() {
        try {
            return KeyFactory.getInstance("RSA");
        }catch (NoSuchAlgorithmException keyFactory){
            throw new RuntimeException("Unknown key algorithm",keyFactory);
        }
    }

    @Override
    public String generateToken(UserPrincipal userPrincipal) {
        return null;
    }

    @Override
    public String resolveToken(HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public boolean isValidToken(HttpServletRequest httpServletRequest) {
        return false;
    }
}
