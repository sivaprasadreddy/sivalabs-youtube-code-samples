package com.sivalabs.myapp.security;

import com.sivalabs.myapp.config.ApplicationProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenHelper {
    private final ApplicationProperties applicationProperties;

    public String getUsernameFromToken(String token) {
        final Claims claims = this.getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setIssuer(applicationProperties.getJwt().getIssuer())
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, applicationProperties.getJwt().getSecret())
                .compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(applicationProperties.getJwt().getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + applicationProperties.getJwt().getExpiresIn() * 1000);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username != null && username.equals(userDetails.getUsername());
    }

    public String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader(applicationProperties.getJwt().getHeader());
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}