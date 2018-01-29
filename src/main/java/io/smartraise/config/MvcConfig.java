package io.smartraise.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/search").setViewName("search");
        registry.addViewController("/event/").setViewName("event");
        registry.addViewController("/organization").setViewName("organization");
        registry.addViewController("/signup").setViewName("TESTsignup");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/member").setViewName("member");
//        registry.addViewController("/upload").setViewName("upload");
//        registry.addViewController("/member/{id}/upload/*").setViewName("profile");
    }
}
