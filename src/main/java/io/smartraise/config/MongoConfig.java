package io.smartraise.config;

import io.smartraise.mongo.CascadeSaveMongoEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public CascadeSaveMongoEventListener userCascadeSaveMongoEventListener() {
        return new CascadeSaveMongoEventListener();
    }
}
