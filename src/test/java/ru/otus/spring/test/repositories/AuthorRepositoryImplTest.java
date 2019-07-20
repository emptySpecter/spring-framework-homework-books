package ru.otus.spring.test.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.AuthorRepositoryImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с авторами ")
@JdbcTest
@Import(AuthorRepositoryImpl.class)
public class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepositoryImpl repository;

    @DisplayName("должен загружать список всех авторов")
    @Test
    public void shouldReturnCorrectAuthorsList() {
        List<Author> authors = repository.getAll();
        assertThat(authors).isNotNull().hasSize(36).allMatch(a -> !a.getName().equals("")).allMatch(a -> !a.getSurname().equals(""));
    }
}
