package ru.otus.spring.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
@EnableAutoConfiguration(exclude = {EmbeddedMongoAutoConfiguration.class})
public class EmbededMongoConfig {
}
