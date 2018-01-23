package io.smartraise.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/home").setViewName("index");
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/signup").setViewName("signup");
//        registry.addViewController("/user").setViewName("user");
//        registry.addViewController("/adminLogin").setViewName("adminLogin");
    }
}
