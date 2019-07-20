package ru.otus.spring;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.util.Map;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Main.class);


        ObjectMapper objectMapper = new ObjectMapper();
        Genre genre = new Genre(2, "Name");
        Author author = new Author(3, "First", "Second");
        Book book = new Book(4, "Book", 15, 10, genre, author);
        Map<String, Object> map = objectMapper.convertValue(book, new TypeReference<Map<String, Object>>() {
        });

        System.out.println("\n\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (Map.Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map) {
                for (Map.Entry subentry : ((Map<String, Object>) value).entrySet()) {
                    System.out.print(subentry.getValue() + " ");
                }
            } else {
                System.out.print(value + " ");
            }
        }
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

        AuthorRepository repository = context.getBean(AuthorRepository.class);
        GenreRepository repository2 = context.getBean(GenreRepository.class);
        BookRepository repository3 = context.getBean(BookRepository.class);
        System.out.println(repository.getById(1));
        System.out.println(repository.getAll());

        System.out.println(repository2.getById(1));
        System.out.println(repository2.getAll());

        System.out.println(repository3.getAll());
        System.out.println("//////////////////////////");
        repository3.getById(20).ifPresent(System.out::println);
        repository.getById(100).ifPresent(b -> System.out.println(repository3.getByAuthor(b)));
        System.out.println(repository3.getByAuthor(new Author(200, "", "")));
        System.out.println("-=======================================-");
        repository3.save(new Book(1000, "Dopio", 10, 13, new Genre(1, "ds"), new Author(20, "ds", "dsd")));
        System.out.println(repository3.getAll());
        repository3.getById(200).ifPresent(System.out::println);
        Console.main(args);

    }
}