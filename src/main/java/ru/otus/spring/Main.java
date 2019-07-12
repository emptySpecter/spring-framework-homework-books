package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.repositories.jdbc.AuthorRepositoryJdbc;
import ru.otus.spring.repositories.jdbc.AuthorRepositoryJdbcImpl;
import ru.otus.spring.repositories.jdbc.BookRepositoryJdbc;
import ru.otus.spring.repositories.jdbc.GenreRepositoryJdbc;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Main.class);

        AuthorRepositoryJdbc repository = context.getBean(AuthorRepositoryJdbc.class);
        GenreRepositoryJdbc repository2 = context.getBean(GenreRepositoryJdbc.class);
        BookRepositoryJdbc repository3 = context.getBean(BookRepositoryJdbc.class);

        System.out.println(repository.getById(1));
        System.out.println(repository.getAll());

        System.out.println(repository2.getById(1));
        System.out.println(repository2.getAll());

        System.out.println(repository3.getAll());
        repository3.getById(200).ifPresent(b -> System.out.println(b));
 //       Console.main(args);

    }
}