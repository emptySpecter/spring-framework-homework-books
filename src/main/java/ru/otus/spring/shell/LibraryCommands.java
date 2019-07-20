package ru.otus.spring.shell;

import org.jline.terminal.Terminal;
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
import java.util.Scanner;

import static ru.otus.spring.shell.TableHelper.getTableModel;

@ShellComponent
public class LibraryCommands {

    private static final String ENTER_BOOK_TITLE = "Enter book title:\n";
    private static final String TITLE_MUST_LESS_THAN_256_SYMBOLS = "Length of book title must less than 256 symbols!\n";
    private static final String ENTER_NUMBER_OF_PAGES = "Enter number of pages:\n";
    private static final String COUNT_MUST_BE_AN_INTEGER_NUMBER = "Page count must be an integer number!\n";
    private static final String ENTER_NUMBER_OF_POINTS = "Enter number of points:\n";
    private static final String POINTS_MUST_BE_AN_INTEGER_NUMBER = "Number of points must be an integer number!\n";
    private static final String ENTER_AUTHOR_ID = "Enter author id:\n";
    private static final String WRONG_AUTHOR_S_ID = "Wrong author's id!\n";
    private static final String ENTER_GENRE_ID = "Enter genre id:\n";
    private static final String WRONG_GENRE_S_ID = "Wrong genre's id!\n";

    private final Terminal terminal;

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public LibraryCommands(AuthorRepository authorRepository, GenreRepository genreRepository, BookRepository bookRepository, @Lazy Terminal terminal) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
        this.terminal = terminal;
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
    public void addBook() throws UnsupportedEncodingException {
        Scanner in = new Scanner(terminal.input(), "UTF-8");
        PrintStream out = new PrintStream(terminal.output(), true, "UTF-8");

        String bookName;
        while (true) {
            out.print(ENTER_BOOK_TITLE);
            bookName = in.nextLine();
            if (bookName.length() <= 255) break;
            out.print(TITLE_MUST_LESS_THAN_256_SYMBOLS);
        }

        int pagecount;
        while (true) {
            out.print(ENTER_NUMBER_OF_PAGES);
            String tmp = in.nextLine();
            if (tmp.matches("[\\d]+")) {
                pagecount = Integer.valueOf(tmp);
                break;
            }
            out.print(COUNT_MUST_BE_AN_INTEGER_NUMBER);
        }


        int points;
        while (true) {
            out.print(ENTER_NUMBER_OF_POINTS);
            String tmp = in.nextLine();
            if (tmp.matches("[\\d]+")) {
                points = Integer.valueOf(tmp);
                break;
            }
            out.print(POINTS_MUST_BE_AN_INTEGER_NUMBER);
        }

        Author author;
        while (true) {
            out.print(ENTER_AUTHOR_ID);
            String tmp = in.nextLine();
            if (tmp.matches("[\\d]+")) {
                int id = Integer.valueOf(tmp);
                try {
                    author = authorRepository.getById(id).get();
                    break;
                } catch (NoSuchElementException e) {
                }
            }
            out.print(WRONG_AUTHOR_S_ID);
        }

        Genre genre;
        while (true) {
            out.print(ENTER_GENRE_ID);
            String tmp = in.nextLine();
            if (tmp.matches("[\\d]+")) {
                int id = Integer.valueOf(tmp);
                try {
                    genre = genreRepository.getById(id).get();
                    break;
                } catch (NoSuchElementException e) {
                }
            }
            out.print(WRONG_GENRE_S_ID);
        }

        out.print("\nYou entered: \n\n");
        out.print("Book title: " + bookName + "\n");
        out.print("Number of pages: " + pagecount + "\n");
        out.print("Number of points: " + points + "\n");
        out.print("Author name and surname: " + author.getName() + " " + author.getSurname() + "\n");
        out.print("Genre: " + genre.getName() + "\n");
        out.print("\nWould you like to add this book to DB? [Y,N]:\n");
        String confirmation = in.nextLine();
        if (confirmation.equals("Y")) {
            bookRepository.save(new Book(0, bookName, pagecount, points, genre, author));
        }

    }
}
