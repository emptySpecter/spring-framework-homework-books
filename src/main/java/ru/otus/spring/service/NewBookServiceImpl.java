package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.io.PrintStream;
import java.util.Scanner;

import static ru.otus.spring.shell.InputHelper.*;
import static ru.otus.spring.shell.TableHelper.getFormattedTableBean;

@Service
public class NewBookServiceImpl implements NewBookService {

    private static final String ENTER_BOOK_TITLE = "Enter book title:\n";
    private static final String TITLE_MUST_LESS_THAN_256_SYMBOLS = "Length of book title must be less than 256 symbols!\n";
    private static final String ENTER_NUMBER_OF_PAGES = "Enter number of pages:\n";
    private static final String COUNT_MUST_BE_AN_INTEGER_NUMBER = "Page count must be an integer number!\n";
    private static final String ENTER_NUMBER_OF_POINTS = "Enter number of points:\n";
    private static final String POINTS_MUST_BE_AN_INTEGER_NUMBER = "Number of points must be an integer number!\n";
    private static final String ENTER_AUTHOR_ID = "Enter author id:\n";
    private static final String WRONG_AUTHOR_S_ID = "Wrong author's id!\n";
    private static final String ENTER_GENRE_ID = "Enter genre id:\n";
    private static final String WRONG_GENRE_S_ID = "Wrong genre's id!\n";

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private PrintStream out;
    private Scanner in;


    public NewBookServiceImpl(AuthorRepository authorRepository, GenreRepository genreRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void newBook(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;

        Book book = new Book();
        book.setId(null);
        book.setName(readStringParameter(in, out, ENTER_BOOK_TITLE, TITLE_MUST_LESS_THAN_256_SYMBOLS));
        book.setPagecount(readIntParameter(in, out, ENTER_NUMBER_OF_PAGES, COUNT_MUST_BE_AN_INTEGER_NUMBER));
        book.setPoints(readIntParameter(in, out, ENTER_NUMBER_OF_POINTS, POINTS_MUST_BE_AN_INTEGER_NUMBER));
        book.setAuthor(readObjectParameter(authorRepository, in, out, ENTER_AUTHOR_ID, WRONG_AUTHOR_S_ID));
        book.setGenre(readObjectParameter(genreRepository, in, out, ENTER_GENRE_ID, WRONG_GENRE_S_ID));

        out.print("\nYou are going to add new book: \n\n");
        out.print(getFormattedTableBean(book, 80));
        out.print("\nWould you like to add this book to DB? [Y,N]:\n");
        String confirmation = in.nextLine();
        if (confirmation.toUpperCase().equals("Y")) {
            bookRepository.save(book);
        }
    }
}
