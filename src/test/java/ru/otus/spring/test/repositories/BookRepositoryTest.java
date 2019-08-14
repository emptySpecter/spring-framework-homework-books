package ru.otus.spring.test.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ComponentScan("ru.otus.spring.config")
@DisplayName("Репозиторий для работы с книгами")
public class BookRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("должен загружать список всех книг")
    @Test
    public void shouldReturnCorrectBookList() {
        List<Book> books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(168).allMatch(b -> !b.getName().equals(""))
                .noneMatch(b -> b.getAuthor() == null).noneMatch(b -> b.getGenre() == null);
    }

    @DisplayName("должен загружать книгу с заданным id")
    @Test
    public void shouldReturnCorrectBookById() {
        Optional<Book> optional = bookRepository.findById("5d4ab4056b907f076c7a8dfc");
        assertThat(optional).isPresent().get().hasFieldOrPropertyWithValue("id", "5d4ab4056b907f076c7a8dfc")
                .hasFieldOrPropertyWithValue("name", "A Daughter of the Snows");
    }

    @DisplayName("должен добовлять книгу в базу")
    @Test
    public void shouldCorrectAddBook() {
        long sizeBefore = bookRepository.count();
        final long pagecount = 15;
        final long points = 10;
        final String bookName = "Book";
        Genre genre = genreRepository.findById("5d4ab41e6b907f076c7a8ea5").get();
        Author author = authorRepository.findById("5d4ab3ea6b907f076c7a8dda").get();
        Book expectedBook = new Book(null, bookName, pagecount, points, genre, author, null);
        Book book = bookRepository.save(expectedBook);
        Book actualBook = bookRepository.findById(book.getId()).get();
        long sizeAfter = bookRepository.count();
        assertEquals(sizeAfter - sizeBefore, 1);
        assertThat(actualBook.getId()).isNotNull();
        assertEquals(expectedBook.getName(), actualBook.getName());
    }
}
