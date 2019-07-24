package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Main {

    private static final String SPRING_SHELL_INTERACTIVE_ENABLED_TRUE = "--spring.shell.interactive.enabled=true";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, SPRING_SHELL_INTERACTIVE_ENABLED_TRUE);
        try {
            Console.main(args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}