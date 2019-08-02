package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@SpringBootApplication
public class Main {

    private static final String SPRING_SHELL_INTERACTIVE_ENABLED_TRUE = "--spring.shell.interactive.enabled=true";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, SPRING_SHELL_INTERACTIVE_ENABLED_TRUE);
    }

    //    @Bean
//    public Jackson2RepositoryPopulatorFactoryBean getRespositoryPopulator() {
//        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
//        factory.setResources(new Resource[]{new ClassPathResource("genres.json")});
//        return factory;
//    }
    @Bean
    public Jackson2RepositoryPopulatorFactoryBean getRespositoryPopulator() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[]{
                new ClassPathResource("authors.json"),
                new ClassPathResource("genres.json"),
                new ClassPathResource("books_with_author_and_genre.json")
        });
        return factory;
    }
}