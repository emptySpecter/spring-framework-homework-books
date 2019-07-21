package ru.otus.spring.shell;

import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.service.BookService;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static ru.otus.spring.shell.TableHelper.getTableModel;

@ShellComponent
public class LibraryCommands {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    public LibraryCommands(AuthorRepository authorRepository, GenreRepository genreRepository,
                           BookRepository bookRepository, @Lazy BookService bookService) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @ShellMethod(value = "Display list of authors", key = {"al", "authors"})
    public void authorsList() {
        List<Author> authors = authorRepository.getAll();
        if (!authors.isEmpty()) {
            TableModel model = getTableModel(authors);
            TableBuilder tableBuilder = new TableBuilder(model);
            tableBuilder.addInnerBorder(BorderStyle.fancy_light);
            tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
            System.out.print(tableBuilder.build().render(80));
        }
    }

    @ShellMethod(value = "Display list of genres", key = {"gl", "genres"})
    public void genresList() {
        List<Genre> genres = genreRepository.getAll();
        if (!genres.isEmpty()) {
            TableModel model = getTableModel(genres);
            TableBuilder tableBuilder = new TableBuilder(model);
            tableBuilder.addInnerBorder(BorderStyle.fancy_light);
            tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
            System.out.print(tableBuilder.build().render(80));
        }
    }

    @ShellMethod(value = "Display list of books", key = {"bl", "books"})
    public void bookList() {
        List<Book> books = bookRepository.getAll();
        if (!books.isEmpty()) {
            TableModel model = getTableModel(books);
            TableBuilder tableBuilder = new TableBuilder(model);
            tableBuilder.addInnerBorder(BorderStyle.fancy_light);
            tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
            System.out.print(tableBuilder.build().render(160));
        }
    }

    @ShellMethod(value = "Add new book", key = {"ab", "add-book"})
    public void addBook() {
        bookService.newBook();
    }
}
