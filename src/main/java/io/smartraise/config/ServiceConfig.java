package io.smartraise.config;

import io.smartraise.service.*;
import io.smartraise.service.deprecated.AdminService;
import io.smartraise.service.deprecated.AdminServiceImpl;
import io.smartraise.service.deprecated.DonorService;
import io.smartraise.service.impl.*;
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

    @Bean
    public AdminService adminService() { return new AdminServiceImpl(); }

    @Bean
    public CharityService charityService() { return new CharityServiceImpl(); }

    @Bean
    public DonationService donationService() { return new DonationServiceImpl(); }

    @Bean
    public DonorService donorService() { return new DonorServiceImpl(); }

    @Bean
    public EventService eventService() { return new EventServiceImpl(); }

    @Bean
    public OrganizationService organizationService() { return new OrganizationServiceImpl(); }

    @Bean
    public RequestService requestService() { return new RequestServiceImpl(); }
}
