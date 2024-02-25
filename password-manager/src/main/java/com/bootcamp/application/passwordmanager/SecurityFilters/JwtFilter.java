package com.bootcamp.application.passwordmanager.SecurityFilters;

import com.bootcamp.application.passwordmanager.ServiceLayer.JwtServiceImpl;
import com.bootcamp.application.passwordmanager.ServiceLayer.UserDetailsImplService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

     private final UserDetailsImplService userDetailsImplService;

    private final JwtServiceImpl jwtServiceImpl;

    @Autowired
    public JwtFilter(UserDetailsImplService userDetailsImplService, JwtServiceImpl jwtService) {
        this.userDetailsImplService = userDetailsImplService;
        this.jwtServiceImpl = jwtService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");

        if(requestHeader == null || !requestHeader.startsWith("Bearer ")){
            log.warn("Request does not contain a jwt, " +
                    "Forwarding the request to next filter");
            filterChain.doFilter(request,response);
            return;
        }
        /*Extracting the jwt from the header*/
        String token = requestHeader.substring(7);

        /*Extracting the username from the jwt*/
        String username = jwtServiceImpl.extractUsername(token);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            /*Getting the UserDetails using the username*/
            UserDetails userDetails = userDetailsImplService.loadUserByUsername(username);

            /*Checking the validity of the jason web token*/
            if(jwtServiceImpl.isValid(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        log.info("Forwarding the request to the next filter chain");
        filterChain.doFilter(request,response);
    }
}
