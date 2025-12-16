package com.nttmk.englishlearningserver.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        if (jwt != null) {
            try {
                String email = JwtProvider.getEmailFromJwtToken(jwt);
                String role = JwtProvider.getRoleFromJwtToken(jwt);

                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            } catch (Exception e) {
                throw new BadCredentialsException("invalid token");
            }
        }

        filterChain.doFilter(request, response);
    }
}