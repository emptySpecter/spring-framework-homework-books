package ru.otus.spring.repositories.jdbc;

import ru.otus.spring.domain.jdbc.Author;
import ru.otus.spring.domain.jdbc.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepositoryJdbc {

    Optional<Genre> getById(long id);

    List<Genre> getAll();

}
