package com.sivalabs.myapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "myapp")
@Data
public class ApplicationProperties {
    private JwtConfig jwt = new JwtConfig();

    @Data
    public static class JwtConfig {
        private static final Long DEFAULT_JWT_TOKEN_EXPIRES = 604_800L;

        private String issuer = "SivaLabs";
        private String header = "Authorization";
        private Long expiresIn = DEFAULT_JWT_TOKEN_EXPIRES;
        private String secret = "";
    }
}