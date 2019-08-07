package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

//    @Query("select b from Book b join fetch b.genre join fetch b.author where b.author=:author")
    List<Book> findByAuthor(Author author);

//    @Override
//    @Query("select b from Book b join fetch b.genre join fetch b.author")
//    List<Book> findAll();

}
