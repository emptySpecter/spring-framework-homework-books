package ru.otus.spring.repositories;

import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Optional<Genre> getById(long id);

    List<Genre> getAll();

}
