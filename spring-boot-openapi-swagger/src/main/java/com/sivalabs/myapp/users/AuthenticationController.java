package com.sivalabs.myapp.users;

import java.time.LocalDateTime;

import com.sivalabs.myapp.config.ApplicationProperties;
import com.sivalabs.myapp.security.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenHelper tokenHelper;
    private final ApplicationProperties applicationProperties;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
            @RequestBody AuthenticationRequest credentials) {
        try {
            Authentication authentication =
               authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(
                          credentials.getUsername(), credentials.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails user = (UserDetails) authentication.getPrincipal();
            String accessToken = tokenHelper.generateToken(user.getUsername());
            return ResponseEntity.ok(getAuthenticationResponse(accessToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private AuthenticationResponse getAuthenticationResponse(String accessToken) {
        return new AuthenticationResponse(
                accessToken,
                LocalDateTime.now().plusSeconds(applicationProperties.getJwt().getExpiresIn())
                );
    }
}