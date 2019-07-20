package ru.otus.spring.test.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.GenreRepositoryImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Репозиторий для работы с жанрами ")
@JdbcTest
@Import(GenreRepositoryImpl.class)
public class GenreRepositoryImplTest {

    @Autowired
    private GenreRepositoryImpl repository;

    @DisplayName("должен загружать список всех жанрoв")
    @Test
    public void shouldReturnCorrectGenresList() {
        List<Genre> genres = repository.getAll();
        assertThat(genres).isNotNull().hasSize(18).allMatch(a -> !a.getName().equals(""));
    }

    @DisplayName("должен загружать жанр с заданным id")
    @Test
    public void shouldReturnCorrectGenreById() {
        Optional<Genre> optional = repository.getById(1);
        assertThat(optional.isPresent());
        Genre genre = optional.get();
        assertEquals(genre.getId(), 1);
        assertEquals(genre.getName(), "Science fiction");
    }
}
