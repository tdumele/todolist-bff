package com.todolist.bff_todolist.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Security security = new Security();
    private final String frontUrl = "http://localhost:3000";

    @Getter
    @Setter
    public static class Security {

        private final PasswordEncoder passwordEncoder = new PasswordEncoder();
        private final Jwt jwt = new Jwt();

        @Getter
        @Setter
        public static class PasswordEncoder {
            private int strength;
            private String secret;
        }

        @Getter
        @Setter
        public static class Jwt {
            private String secret;
            private long expiration;
        }
    }
}
