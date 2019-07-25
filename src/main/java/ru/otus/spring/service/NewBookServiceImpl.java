package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Service
public class NewBookServiceImpl implements NewBookService {

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
        book.setId(0L);
        book.setName(readBookName());
        book.setPagecount(readPagecount());
        book.setPoints(readPoints());
        book.setAuthor(readAuthor());
        book.setGenre(readGenre());

        out.print("\nYou entered: \n\n");
        out.print("Book title: " + book.getName() + "\n");
        out.print("Number of pages: " + book.getPagecount() + "\n");
        out.print("Number of points: " + book.getPoints() + "\n");
        out.print("Author name and surname: " + book.getAuthor().getName() + " " + book.getAuthor().getSurname() + "\n");
        out.print("Genre: " + book.getGenre().getName() + "\n");
        out.print("\nWould you like to add this book to DB? [Y,N]:\n");
        String confirmation = in.nextLine();
        if (confirmation.equals("Y")) {
            bookRepository.save(book);
        }


    }

    private String readBookName() {
        String bookName;
        while (true) {
            out.print(ENTER_BOOK_TITLE);
            bookName = in.nextLine();
            if (bookName.length() <= 255) break;
            out.print(TITLE_MUST_LESS_THAN_256_SYMBOLS);
        }
        return bookName;
    }

    private int readPagecount() {
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
        return pagecount;
    }

    private int readPoints() {
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
        return points;
    }

    private Author readAuthor() {
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
        return author;
    }

    private Genre readGenre() {
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
        return genre;
    }

}
