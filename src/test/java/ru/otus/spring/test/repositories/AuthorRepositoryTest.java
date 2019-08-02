package ru.otus.spring.test.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с авторами ")
@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @DisplayName("должен загружать список всех авторов")
    @Test
    public void shouldReturnCorrectAuthorsList() {
        List<Author> authors = repository.findAll();
        assertThat(authors).isNotNull().hasSize(36).allMatch(a -> !a.getName().equals("")).allMatch(a -> !a.getSurname().equals(""));
    }

    @DisplayName("должен загружать автора с заданным id")
    @Test
    public void shouldReturnCorrectAuthorById() {
        Optional<Author> optional = repository.findById(1L);
//        assertThat(optional.isPresent()).isTrue();
        assertThat(optional).isPresent().get().hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("surname", "Howells");
    }
}