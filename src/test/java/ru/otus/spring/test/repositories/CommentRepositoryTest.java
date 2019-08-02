package ru.otus.spring.test.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.CommentRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ComponentScan("ru.otus.spring.config")
@DisplayName("Репозиторий для работы с комментариями ")
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("должен загружать список всех жанрoв")
    @Test
    public void shouldReturnCorrectGenresList() {
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments).isNotNull().hasSize(3).allMatch(a -> !a.getText().equals(""));
    }

    @DisplayName("должен добовлять комментарий к книге в базу")
    @Test
    public void shouldCorrectAddBook() {
        final long commentId = 0;
        final long bookId = 1;
        final String text = "comment.";
        Comment expectedComment = new Comment(commentId, text, bookId);
        long newId = (long) em.persistAndGetId(expectedComment);
        Comment actualComment = commentRepository.findById(newId).get();
        assertThat(actualComment.getId()).isNotNull();
        assertEquals(text, actualComment.getText());

    }

}
