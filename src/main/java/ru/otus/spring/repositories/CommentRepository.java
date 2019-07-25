package ru.otus.spring.repositories;

import ru.otus.spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    void save(Comment comment);

    Optional<Comment> getById(long id);

    List<Comment> getAll();

}
