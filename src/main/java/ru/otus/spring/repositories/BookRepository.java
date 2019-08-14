package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByAuthor(Author author);

    List<Book> findByCommentsNotNull();
}
