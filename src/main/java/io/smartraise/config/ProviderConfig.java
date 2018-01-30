package io.smartraise.config;

import io.smartraise.security.CustomAuthProvider;
import io.smartraise.security.CustomAuthenticationFailureHandler;
import io.smartraise.util.ImageURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProviderConfig {

    @Bean
    public CustomAuthProvider custAuthProvider() {return new CustomAuthProvider(); }

//    @Bean
//    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
//        return new CustomAuthenticationFailureHandler();
//    }

    @Bean
    public ImageURI imageURI() {
        return new ImageURI();
    }
}
