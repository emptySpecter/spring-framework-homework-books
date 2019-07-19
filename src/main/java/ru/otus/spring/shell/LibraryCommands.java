package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import static ru.otus.spring.shell.TableHelper.getTableModel;

@ShellComponent
public class LibraryCommands {

    private final Terminal terminal;

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public LibraryCommands(AuthorRepository authorRepository, GenreRepository genreRepository, BookRepository bookRepository,@Lazy Terminal terminal) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
        this.terminal = terminal;
    }

    @ShellMethod(value = "Display list of authors", key = {"al", "authors"})
    public void authorsList() {
        List<Author> authors = authorRepository.getAll();

        TableModel model = getTableModel(authors);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        System.out.print(tableBuilder.build().render(80));
    }

    @ShellMethod(value = "Display list of genres", key = {"gl", "genres"})
    public void genresList() {
        List<Genre> genres = genreRepository.getAll();

        TableModel model = getTableModel(genres);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        System.out.print(tableBuilder.build().render(80));
    }

    @ShellMethod(value = "Display list of books", key = {"bl", "books"})
    public void bookList() {
        List<Book> books = bookRepository.getAll();

        TableModel model = getTableModel(books);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        System.out.print(tableBuilder.build().render(160));
    }

    @ShellMethod(value = "Add new book", key = {"ab","add-book"})
    public void addBook() throws UnsupportedEncodingException {
        Scanner in = new Scanner(terminal.input(), "UTF-8");
        PrintStream out = new PrintStream(terminal.output(), true, "UTF-8");

        String bookName = "";
        while (true) {
            out.print("Enter book title:\n");
            bookName = in.nextLine();
            if (bookName.length() <= 255) break;
            out.print("Length of book title must less than 256 symbols!\n");
        }

        int pagecount = 0;
        while (true) {
            out.print("Enter number of pages:\n");
            String tmp  = in.nextLine();
            if (tmp.matches("[\\d]+")) {
                pagecount = Integer.valueOf(tmp);
                break;
            }
            out.print("Page count must be an integer number!\n");
        }


        int points = 0;
        while (true) {
            out.print("Enter number of points:\n");
            String tmp  = in.nextLine();
            if (tmp.matches("[\\d]+")) {
                points = Integer.valueOf(tmp);
                break;
            }
            out.print("Number of points must be an integer number!\n");
        }

        Author author;
        while (true){
            out.print("Enter author id:\n");
            String tmp = in.nextLine();
            if (tmp.matches("[\\d]+")){
                int id = Integer.valueOf(tmp);
                try {
                    author = authorRepository.getById(id).get();
                    break;
                } catch (NoSuchElementException e) {
                }
            }
            out.print("Wrong author's id!\n");
        }
        author.getId();
    }
}
