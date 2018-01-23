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
        registry.addViewController("/search").setViewName("searchPage");
        registry.addViewController("/eventPage").setViewName("eventPage");
        registry.addViewController("/organization").setViewName("organization");
        registry.addViewController("/signUp").setViewName("signUp");
    }
}
