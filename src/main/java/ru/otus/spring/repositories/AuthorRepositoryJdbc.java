package ru.otus.spring.repositories;

import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositoryJdbc {

    Optional<Author> getById(long id);

    List<Author> getAll();

}
