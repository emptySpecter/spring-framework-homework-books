package ru.otus.spring.test.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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

@DisplayName("Репозиторий для работы с книгами")
@DataJpaTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager em;

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
        Optional<Book> optional = bookRepository.findById(1L);
        assertThat(optional).isPresent().get().hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("name", "A Daughter of the Snows");
    }

    @DisplayName("должен добовлять книгу в базу")
    @Test
    public void shouldCorrectAddBook() {
        final long bookId = 0;
        final long pagecount = 15;
        final long points = 10;
        final String bookName = "Book";
        Genre genre = genreRepository.findById(2L).get();
        // if I create just a new genre and author then persist dosen't work at all!
        Author author = authorRepository.findById(3L).get();
        Book expectedBook = new Book(bookId, bookName, pagecount, points, genre, author);
        long newId = (long) em.persistAndGetId(expectedBook);
        Book actuallBook = bookRepository.findById(newId).get();
        assertThat(actuallBook.getId()).isNotNull();
        assertEquals(expectedBook.getName(), actuallBook.getName());
    }
}
