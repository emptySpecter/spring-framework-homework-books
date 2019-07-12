package ru.otus.spring.repositories.jdbc;

import ru.otus.spring.domain.jdbc.Author;

import java.util.List;

public interface AuthorRepositoryJdbc {

    Author getById(long id);

    List<Author> getAll();

}
