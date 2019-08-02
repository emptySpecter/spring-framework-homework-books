package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
