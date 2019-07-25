package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(Comment comment) {

    }

    @Override
    public Optional<Comment> getById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> getAll() {
        return em.createQuery("select c from Comment c", Comment.class).getResultList();
    }
}
