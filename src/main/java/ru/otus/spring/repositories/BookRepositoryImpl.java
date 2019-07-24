package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
        } else {
            em.merge(book);
        }

    }

    @Override
    public List<Book> getByAuthor(Author author) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b join fetch b.genre join fetch b.author where b.author=:author",
                Book.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b join fetch b.genre join fetch b.author",
                Book.class);
        return query.getResultList();
    }
}
