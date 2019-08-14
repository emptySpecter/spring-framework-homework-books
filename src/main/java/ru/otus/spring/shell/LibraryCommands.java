package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.service.AddBookService;
import ru.otus.spring.service.NewCommentService;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static ru.otus.spring.shell.TableHelper.getFormattedTableBean;
import static ru.otus.spring.shell.TableHelper.getFormattedTableList;

@RequiredArgsConstructor
@ShellComponent
public class LibraryCommands {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final AddBookService addBookService;
    private final NewCommentService newCommentService;
    private PrintStream out;
    private Scanner in;


    @Autowired
    public void setTerminal(Terminal terminal) throws UnsupportedEncodingException {
        in = new Scanner(terminal.input(), "UTF-8");
        out = new PrintStream(terminal.output(), true, "UTF-8");
    }

    @ShellMethod(value = "Display list of authors", key = {"al", "authors"})
    public void authorsList() {
        List<Author> authors = authorRepository.findAll();
        if (!authors.isEmpty()) {
            out.print(getFormattedTableList(authors, 80));
        }
    }

    @ShellMethod(value = "Display list of genres", key = {"gl", "genres"})
    public void genresList() {
        List<Genre> genres = genreRepository.findAll();
        if (!genres.isEmpty()) {
            out.print(getFormattedTableList(genres, 80));
        }
    }


    @ShellMethod(value = "Display list of comments for book with id (for all books if id=0)", key = {"cl", "comments"})
    public void commentsList(@ShellOption(value = {"-id"}, defaultValue = "") String id) {
        if (id.isEmpty()) {
            List<Book> books = bookRepository.findByCommentsNotNull();
            if (!books.isEmpty()) {
                out.print(getFormattedTableList(books, 160));
            }
        } else {
            try {
                Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found"));
                List<Comment> comments = book.getComments();
                if (comments != null) {
                    out.println("Comments:");
                    out.print(getFormattedTableList(comments, 80));
                } else {
                    out.print("No comments ");
                }
                out.println("for the book:");
                out.print(getFormattedTableBean(book, 80));
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @ShellMethod(value = "Display list of books (or 'bl -id author_id' for book with the corresponding author)", key = {"bl", "books"})
    public void bookList(@ShellOption(value = {"-id"}, defaultValue = "") String id) {
        List<Book> books;
        if (id.isEmpty()) {
            books = bookRepository.findAll();
        } else {
            books = bookRepository.findByAuthor(authorRepository.findById(id).get());
        }
        if (!books.isEmpty()) {
            out.print(getFormattedTableList(books, 160));
        }
    }

    @ShellMethod(value = "Add new book", key = {"ab", "add-book"})
    public void addBook() {
        addBookService.newBook(in, out);
    }

    @ShellMethod(value = "Add new comment", key = {"ac", "add-cooment"})
    public void addComment() {
        newCommentService.newComment(in, out);
    }
}
