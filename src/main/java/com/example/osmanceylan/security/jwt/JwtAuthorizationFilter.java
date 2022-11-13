package com.example.osmanceylan.security.jwt;

import com.example.osmanceylan.security.jwt.IJwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@RequiredArgsConstructor
public class JwtAuthorizationFilter  extends OncePerRequestFilter {
    @Autowired
    private IJwtProvider iJwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //request
        Authentication authentication = iJwtProvider.getAuthentication(request);

        //authentication
        if (iJwtProvider.isValidateToken(request) && authentication != null)
            SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }

}
