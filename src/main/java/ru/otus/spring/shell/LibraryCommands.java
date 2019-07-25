package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.service.NewBookService;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

import static ru.otus.spring.shell.TableHelper.getTableModel;

@RequiredArgsConstructor
@ShellComponent
public class LibraryCommands {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final NewBookService newBookService;
    private PrintStream out;
    private Scanner in;


    @Autowired
    public void setTerminal(Terminal terminal) throws UnsupportedEncodingException {
        in = new Scanner(terminal.input(), "UTF-8");
        out = new PrintStream(terminal.output(), true, "UTF-8");
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


    @ShellMethod(value = "Display list of comments", key = {"cl", "comments"})
    public void commentsList() {
        List<Comment> comments = commentRepository.getAll();
        if (!comments.isEmpty()) {
            TableModel model = getTableModel(comments);
            TableBuilder tableBuilder = new TableBuilder(model);
            tableBuilder.addInnerBorder(BorderStyle.fancy_light);
            tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
            System.out.print(tableBuilder.build().render(160));
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
        newBookService.newBook(in, out);
    }
}
