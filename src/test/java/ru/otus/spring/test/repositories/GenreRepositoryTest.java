package ru.otus.spring.test.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ComponentScan("ru.otus.spring.config")
@DisplayName("Репозиторий для работы с жанрами ")
public class GenreRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private GenreRepository repository;

    @DisplayName("должен загружать список всех жанрoв")
    @Test
    public void shouldReturnCorrectGenresList() {
        List<Genre> genres = repository.findAll();
        assertThat(genres).isNotNull().hasSize(18).allMatch(a -> !a.getName().equals(""));
    }

    @DisplayName("должен загружать жанр с заданным id")
    @Test
    public void shouldReturnCorrectGenreById() {
        Optional<Genre> optional = repository.findById("5d4ab41e6b907f076c7a8ea4");
        assertThat(optional).isPresent().get().hasFieldOrPropertyWithValue("id", "5d4ab41e6b907f076c7a8ea4")
                .hasFieldOrPropertyWithValue("name", "Science fiction");
    }
}
