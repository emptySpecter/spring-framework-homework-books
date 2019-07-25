package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> getAll() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();
    }
}
