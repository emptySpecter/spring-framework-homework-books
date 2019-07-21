package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    private static final String SPRING_SHELL_INTERACTIVE_ENABLED_TRUE = "--spring.shell.interactive.enabled=true";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, SPRING_SHELL_INTERACTIVE_ENABLED_TRUE);
    }
}