package io.smartraise.config;

import io.smartraise.security.CustomAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProviderConfig {

    @Bean
    public CustomAuthProvider custAuthProvider() {return new CustomAuthProvider(); }
}
