package ru.otus.spring.repositories;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJdbc {
    void save(Book book);

    Optional<Book> getById(long id);

    List<Book> getByAuthor(Author author);

    List<Book> getAll();
}
