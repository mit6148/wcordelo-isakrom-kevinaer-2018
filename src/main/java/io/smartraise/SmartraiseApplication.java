package io.smartraise;

import io.smartraise.config.MongoConfig;
import io.smartraise.config.MvcConfig;
import io.smartraise.config.SecurityConfig;
import io.smartraise.config.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ServiceConfig.class, MvcConfig.class, SecurityConfig.class, MongoConfig.class})
public class SmartraiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartraiseApplication.class, args);
	}
}
