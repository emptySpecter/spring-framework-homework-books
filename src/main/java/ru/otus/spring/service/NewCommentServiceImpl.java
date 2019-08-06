package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.BookRepository;

import java.io.PrintStream;
import java.util.Scanner;

import static ru.otus.spring.shell.InputHelper.readObjectParameter;
import static ru.otus.spring.shell.InputHelper.readStringParameter;
import static ru.otus.spring.shell.TableHelper.getFormattedTableBean;

@Service
@RequiredArgsConstructor
public class NewCommentServiceImpl implements NewCommentService {

    private static final String ENTER_BOOK_ID = "Enter book id:\n";
    private static final String WRONG_BOOK_S_ID = "Wrong book's id!\n";
    private static final String ENTER_COMMENT_TEXT = "Enter comment text:\n";
    private static final String TEXT_MUST_LESS_THAN_256_SYMBOLS = "Length of comment must be less than 256 symbols!\n";

    private final BookRepository bookRepository;

    private PrintStream out;
    private Scanner in;

    @Override
    @Transactional
    public void newComment(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;

        Comment comment = new Comment();
        comment.setId(0L);
        Book book = readObjectParameter(bookRepository, in, out, ENTER_BOOK_ID, WRONG_BOOK_S_ID);
        comment.setBookId(book.getId());
        comment.setText(readStringParameter(in, out, ENTER_COMMENT_TEXT, TEXT_MUST_LESS_THAN_256_SYMBOLS));

        out.print("\nYou are going to add new comment: \n");
        out.print(comment.getText());
        out.print("\nTo the book: \n");
        out.print(getFormattedTableBean(book, 80));
        out.print("\nWould you like to add this comment to DB? [Y,N]:\n");
        String confirmation = in.nextLine();
        if (confirmation.toUpperCase().equals("Y")) {
            book.getComments().add(comment);
            bookRepository.save(book);
        }
    }
}
