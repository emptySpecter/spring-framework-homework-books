package ru.otus.spring.test.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.AuthorRepositoryImpl;
import ru.otus.spring.repositories.BookRepositoryImpl;
import ru.otus.spring.repositories.GenreRepositoryImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Репозиторий для работы с книгами")
@DataJpaTest
@Import({BookRepositoryImpl.class, AuthorRepositoryImpl.class, GenreRepositoryImpl.class})
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BookRepositoryImplTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookRepositoryImpl bookRepository;

    @Autowired
    private AuthorRepositoryImpl authorRepository;

    @Autowired
    private GenreRepositoryImpl genreRepository;

    @DisplayName("должен загружать список всех книг")
    @Test
    public void shouldReturnCorrectBookList() {
        List<Book> books = bookRepository.getAll();
        assertThat(books).isNotNull().hasSize(168).allMatch(b -> !b.getName().equals(""))
                .noneMatch(b -> b.getAuthor() == null).noneMatch(b -> b.getGenre() == null);
    }

    @DisplayName("должен загружать книгу с заданным id")
    @Test
    public void shouldReturnCorrectBookById() {
        Optional<Book> optional = bookRepository.getById(1);
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
        Genre genre = genreRepository.getById(2).get();
        // if I create just a new genre and author then persist dosen't work at all!
        Author author = authorRepository.getById(3).get();
        Book expectedBook = new Book(bookId, bookName, pagecount, points, genre, author);
        long newId = (long) em.persistAndGetId(expectedBook);
        Book actuallBook = bookRepository.getById(newId).get();
        assertThat(actuallBook.getId()).isNotNull();
        assertEquals(expectedBook.getName(), actuallBook.getName());
    }
}
