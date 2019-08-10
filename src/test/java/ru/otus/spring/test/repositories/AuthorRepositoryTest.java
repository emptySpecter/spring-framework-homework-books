package ru.otus.spring.test.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ComponentScan("ru.otus.spring.config")
@DisplayName("Репозиторий для работы с авторами ")
public class AuthorRepositoryTest extends AbstractRepositoryTest {

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
        Optional<Author> optional = repository.findById("5d4ab3ea6b907f076c7a8dd8");
        assertThat(optional).isPresent().get().hasFieldOrPropertyWithValue("id", "5d4ab3ea6b907f076c7a8dd8")
                .hasFieldOrPropertyWithValue("surname", "Howells");
    }
}
