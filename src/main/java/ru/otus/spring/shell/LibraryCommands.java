package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import ru.otus.spring.domain.*;
import ru.otus.spring.repositories.*;
import ru.otus.spring.service.NewBookService;
import ru.otus.spring.service.NewCommentService;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

import static ru.otus.spring.shell.TableHelper.getTableModelBean;
import static ru.otus.spring.shell.TableHelper.getTableModelList;

@RequiredArgsConstructor
@ShellComponent
public class LibraryCommands {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final BookWithCommentsRepository bookWithCommentsRepository;
    private final CommentRepository commentRepository;
    private final NewBookService newBookService;
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
            TableModel model = getTableModelList(authors);
            TableBuilder tableBuilder = new TableBuilder(model);
            tableBuilder.addInnerBorder(BorderStyle.fancy_light);
            tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
            out.print(tableBuilder.build().render(80));
        }
    }

    @ShellMethod(value = "Display list of genres", key = {"gl", "genres"})
    public void genresList() {
        List<Genre> genres = genreRepository.findAll();
        if (!genres.isEmpty()) {
            TableModel model = getTableModelList(genres);
            TableBuilder tableBuilder = new TableBuilder(model);
            tableBuilder.addInnerBorder(BorderStyle.fancy_light);
            tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
            out.print(tableBuilder.build().render(80));
        }
    }


    @ShellMethod(value = "Display list of comments for book with id (for all books if id=0)", key = {"cl", "comments"})
    public void commentsList(@ShellOption(value = {"-id"}, defaultValue = "0") long id) {
        if (id == 0) {
            List<BookWithComments> books = bookWithCommentsRepository.findAll();
//            List<Book> books = bookRepository.findAll();
            if (!books.isEmpty()) {
                TableModel model = getTableModelList(books);
                TableBuilder tableBuilder = new TableBuilder(model);
                tableBuilder.addInnerBorder(BorderStyle.fancy_light);
                tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
                out.print(tableBuilder.build().render(160));
            }
        } else {
            TableBuilder tableBuilder;
            TableModel model;
            BookWithComments book = bookWithCommentsRepository.findById(id).get();
            List<Comment> comments = book.getComments();
            if (comments.size() > 0) {
                out.println("Comments;");
                model = getTableModelList(comments);
                tableBuilder = new TableBuilder(model);
                tableBuilder.addFullBorder(BorderStyle.fancy_light);
                out.print(tableBuilder.build().render(80));
            } else {
                out.print("No comments ");
            }
            out.println("for the book:");
            model = getTableModelBean(book.getBook());
            tableBuilder = new TableBuilder(model);
            tableBuilder.addFullBorder(BorderStyle.fancy_light);
            out.print(tableBuilder.build().render(80));
        }
    }

    @ShellMethod(value = "Display list of books (or 'bl -id author_id' for book with the corresponding author)", key = {"bl", "books"})
    public void bookList(@ShellOption(value = {"-id"}, defaultValue = "0") long id) {
        List<Book> books;
        if (id == 0) {
            books = bookRepository.findAll();
        } else {
            books = bookRepository.findByAuthor(authorRepository.findById(id).get());
        }
        if (!books.isEmpty()) {
            TableModel model = getTableModelList(books);
            TableBuilder tableBuilder = new TableBuilder(model);
            tableBuilder.addInnerBorder(BorderStyle.fancy_light);
            tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
            out.print(tableBuilder.build().render(160));
        }
    }

    @ShellMethod(value = "Add new book", key = {"ab", "add-book"})
    public void addBook() {
        newBookService.newBook(in, out);
    }

    @ShellMethod(value = "Add new comment", key = {"ac", "add-cooment"})
    public void addComment() {
        newCommentService.newComment(in, out);
    }
}
