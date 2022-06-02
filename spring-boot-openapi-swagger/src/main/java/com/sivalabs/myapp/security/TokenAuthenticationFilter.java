package com.sivalabs.myapp.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenHelper tokenHelper;
    private final UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(TokenHelper tokenHelper, UserDetailsService userDetailsService) {
        this.tokenHelper = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String authToken = tokenHelper.getToken(request);

        if (authToken != null) {
            String email = tokenHelper.getUsernameFromToken(authToken);
            if (email != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                if (tokenHelper.validateToken(authToken, userDetails)) {
                    TokenBasedAuthentication authentication =
                            new TokenBasedAuthentication(authToken, userDetails);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}