package ru.otus.spring.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
public class ApplicationConfig {

    @Bean
    @ConditionalOnProperty(name = "spring.data.json", havingValue = "")
    public Jackson2RepositoryPopulatorFactoryBean getRespositoryPopulator(AppProps props) {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[]{
                new ClassPathResource(props.getJson())
        });
        return factory;
    }

}
