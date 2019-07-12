package ru.otus.spring.repositories.jdbc;

import ru.otus.spring.domain.jdbc.Author;
import ru.otus.spring.domain.jdbc.Genre;

import java.util.List;

public interface GenreRepositoryJdbc {

    Genre getById(long id);

    List<Genre> getAll();

}
