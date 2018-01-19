package io.smartraise.config;

import io.smartraise.service.MemberService;
import io.smartraise.service.impl.CredentialServiceImpl;
import io.smartraise.service.CredentialService;
import io.smartraise.service.impl.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public CredentialService credentialService() {
        return new CredentialServiceImpl();
    }

    @Bean
    public MemberService memberService() { return new MemberServiceImpl(); }
}
