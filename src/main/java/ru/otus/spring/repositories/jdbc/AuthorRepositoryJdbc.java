package ru.otus.spring.repositories.jdbc;

import ru.otus.spring.domain.jdbc.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositoryJdbc {

    Optional<Author> getById(long id);

    List<Author> getAll();

}
