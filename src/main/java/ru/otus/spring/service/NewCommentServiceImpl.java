package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class NewCommentServiceImpl implements NewCommentService{

    private static final String ENTER_BOOK_ID = "Enter book id:\n";
    private static final String WRONG_BOOK_S_ID = "Wrong book's id!\n";
    private static final String ENTER_GENRE_ID = "Enter genre id:\n";
    private static final String TITLE_MUST_LESS_THAN_256_SYMBOLS = "Length of book title must less than 256 symbols!\n";

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    private PrintStream out;
    private Scanner in;

    @Override
    @Transactional
    public void newComment(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;

        Comment comment = new Comment();
        comment.setId(0L);
        Book book = readBook();
        comment.setBookId(book.getId());
        comment.setText("192000001");

        out.print("\nYou entered: \n\n");
        out.print("\nComment text: " + comment.getText() + "\n");
        out.print("For book: \n");
        out.print("Book title: " + book.getName() + "\n");
        out.print("Number of pages: " + book.getPagecount() + "\n");
        out.print("Number of points: " + book.getPoints() + "\n");
        out.print("Author name and surname: " + book.getAuthor().getName() + " " + book.getAuthor().getSurname() + "\n");
        out.print("Genre: " + book.getGenre().getName() + "\n");
        out.print("\nWould you like to add this comment to DB? [Y,N]:\n");
        String confirmation = in.nextLine();
        if (confirmation.equals("Y")) {
            commentRepository.save(comment);
        }
    }

    private Book readBook(){
        Book book;
        while (true) {
            out.print(ENTER_BOOK_ID);
            String tmp = in.nextLine();
            if (tmp.matches("[\\d]+")) {
                long id = Long.valueOf(tmp);
                try {
                    book = bookRepository.getById(id).get();
                    break;
                } catch (NoSuchElementException e) {
                }
            }
            out.print(WRONG_BOOK_S_ID);
        }
        return book;
    }

}
