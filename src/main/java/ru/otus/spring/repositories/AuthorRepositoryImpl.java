package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> getAll() {
        return  em.createQuery("select a from Author a", Author.class).getResultList();
    }
}
