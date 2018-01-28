package io.smartraise.config;

import io.smartraise.service.*;
import io.smartraise.service.deprecated.AdminService;
import io.smartraise.service.deprecated.AdminServiceImpl;
import io.smartraise.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

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
    public EventService eventService() { return new EventServiceImpl(); }

    @Bean
    public OrganizationService organizationService() { return new OrganizationServiceImpl(); }

    @Bean
    public RequestService requestService() { return new RequestServiceImpl(); }

    @Bean
    public ImageService imageService() { return new ImageServiceImpl(); }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multi = new CommonsMultipartResolver();
        multi.setMaxUploadSize(1000000);
        return multi;
    }

    @Bean
    @Order(0)
    public MultipartFilter multipartFilter() {
        return new MultipartFilter();
    }
}
