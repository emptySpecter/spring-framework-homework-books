package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static ru.otus.spring.shell.TableHelper.getTableModelBean;

@Service
@RequiredArgsConstructor
public class NewCommentServiceImpl implements NewCommentService {

    private static final String ENTER_BOOK_ID = "Enter book id:\n";
    private static final String WRONG_BOOK_S_ID = "Wrong book's id!\n";
    private static final String ENTER_COMMENT_TEXT = "Enter comment text:\n";
    private static final String TEXT_MUST_LESS_THAN_256_SYMBOLS = "Length of comment must be less than 256 symbols!\n";

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
        comment.setText(readComment());

        out.print("\nYou are going to add new comment: \n");
        out.print(comment.getText());
        out.print("\nTo the book: \n");

        TableModel model = getTableModelBean(book);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addFullBorder(BorderStyle.fancy_light);
        out.print(tableBuilder.build().render(80));
        out.print("\nWould you like to add this comment to DB? [Y,N]:\n");
        String confirmation = in.nextLine();
        if (confirmation.equals("Y")) {
            commentRepository.save(comment);
        }
    }

    private Book readBook() {
        Book book;
        while (true) {
            out.print(ENTER_BOOK_ID);
            String tmp = in.nextLine();
            if (tmp.matches("[\\d]+")) {
                long id = Long.parseLong(tmp);
                try {
                    book = bookRepository.findById(id).get();
                    break;
                } catch (NoSuchElementException e) {
                }
            }
            out.print(WRONG_BOOK_S_ID);
        }
        return book;
    }

    private String readComment() {
        String commentText;
        while (true) {
            out.print(ENTER_COMMENT_TEXT);
            commentText = in.nextLine();
            if (commentText.length() <= 255) break;
            out.print(TEXT_MUST_LESS_THAN_256_SYMBOLS);
        }
        return commentText;
    }

}
