package ru.otus.spring.repositories.jdbc;

import ru.otus.spring.domain.jdbc.Book;
import ru.otus.spring.domain.jdbc.Genre;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJdbc {
    void insert(Book book);

    Optional<Book> getById(long id);

    List<Book> getAll();
}
