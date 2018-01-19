package io.smartraise.config;

import io.smartraise.security.CustAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProviderConfig {

    @Bean
    public CustAuthProvider custAuthProvider() {return new CustAuthProvider(); }
}
