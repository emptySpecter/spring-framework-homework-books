package ru.otus.spring.test.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepositoryImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Репозиторий для работы с книгами")
@JdbcTest
@Import(BookRepositoryImpl.class)
public class BookRepositoryImplTest {

    @Autowired
    private BookRepositoryImpl repository;

    @DisplayName("должен загружать список всех книг")
    @Test
    public void shouldReturnCorrectBookList() {
        List<Book> books = repository.getAll();
        assertThat(books).isNotNull().hasSize(168).allMatch(b -> !b.getName().equals(""))
                .noneMatch(b -> b.getAuthor() == null).noneMatch(b -> b.getGenre() == null);
    }

    @DisplayName("должен загружать книгу с заданным id")
    @Test
    public void shouldReturnCorrectBookById() {
        Optional<Book> optional = repository.getById(1);
        assertThat(optional.isPresent());
        Book book = optional.get();
        assertEquals(book.getId(), 1);
        assertEquals(book.getName(), "A Daughter of the Snows");
    }

    @DisplayName("должен добовлять книгу в базу")
    @Test
    public void shouldCorrectAddBook() {
        int beforeCount = repository.getAll().size();
        Genre genre = new Genre(2, "Name");
        Author author = new Author(3, "First", "Second");
        Book book = new Book(4, "Book", 15, 10, genre, author);
        repository.save(book);
        int afterCount = repository.getAll().size();
        assertEquals(afterCount - beforeCount, 1);
    }
}
