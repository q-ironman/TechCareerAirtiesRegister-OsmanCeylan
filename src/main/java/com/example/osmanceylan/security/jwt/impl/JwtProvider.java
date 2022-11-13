package com.example.osmanceylan.security.jwt.impl;

import com.example.osmanceylan.security.UserPrincipal;
import com.example.osmanceylan.security.jwt.IJwtProvider;
import com.example.osmanceylan.service.impl.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// Spring introduction
@Component
public class JwtProvider implements IJwtProvider {
    private static final String JWT_TOKEN_PREFIX = "Bearer";
    private static final String JWT_HEADER_BEARER = "Authorization";

    //application.properties
    @Value("${authentication.jwt.expiration-ms}")
    private Long JWT_EXPIRATION_MS;
    // Public Key
    private PublicKey jwtPublicKey;
    // Private Key
    private PrivateKey jwtPrivateKey;

    public JwtProvider(@Value("${authentication.jwt.public-key}") String jwtPublicKeyData,
                       @Value("${authentication.jwt.private-key}")String jwtPrivateKeyData) {

        try {
            //Spring Security RSA Algorithm
            KeyFactory keyFactory = getKeyFactory();
            Base64.Decoder decoder =Base64.getDecoder();
            //Private public keys for Spring Security
            X509EncodedKeySpec publicKeyEncoder = new X509EncodedKeySpec(decoder.decode(jwtPublicKeyData));
            jwtPublicKey =keyFactory.generatePublic(publicKeyEncoder);
            PKCS8EncodedKeySpec privateKeyEncoeder = new PKCS8EncodedKeySpec(decoder.decode(jwtPrivateKeyData.getBytes()));
            jwtPrivateKey = keyFactory.generatePrivate(privateKeyEncoeder);
        }catch (Exception e){

            e.printStackTrace();
            throw new RuntimeException("key invalid",e);
        }

    }

    //1st Method
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
        String roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("roles",roles)
                .claim("userId",userPrincipal.getId())
                .setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION_MS))
                .signWith(jwtPrivateKey, SignatureAlgorithm.RS512)
                .compact();

    }


    //2nd Method JWT Seperation
    //HEADER: Bearer => 7
    @Override
    public String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(JWT_HEADER_BEARER);
        if (bearerToken.startsWith(JWT_TOKEN_PREFIX) && bearerToken != null){
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest httpServletRequest) {
        String tokenData = resolveToken(httpServletRequest);
        if (tokenData == null)
            return null;
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtPublicKey).build().parseClaimsJws(tokenData).getBody();
        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);
        List<GrantedAuthority> grantedAuthorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(rol -> rol.startsWith("ROLE_") ? rol: "ROLE_"+rol)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        //UserDetails
        UserDetails userDetails = new UserPrincipal(userId,username,null) ;
        Authentication authentication = username!= null ? new UsernamePasswordAuthenticationToken(userDetails,null,grantedAuthorities): null;
        return authentication;
    }

    @Override
    public boolean isValidToken(HttpServletRequest httpServletRequest) {
        String tokenData = resolveToken(httpServletRequest);
        if (tokenData == null)
            return false;
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtPublicKey).build().parseClaimsJws(tokenData).getBody();

        //
        if (claims.getExpiration().before(new Date()))
            return false;
        return true;
    }
}
