package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookWithComments;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Book book) {
        if (book.getId() <= 0) em.persist(book);
        else em.merge(book);
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        return em.createQuery("select b from Book b join fetch b.genre join fetch b.author where b.author=:author", Book.class)
                .setParameter("author", author).getResultList();
    }

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select b from Book b join fetch b.genre join fetch b.author", Book.class).getResultList();
    }

    @Override
    public Optional<BookWithComments> getByIdWithComments(long id) {
        return Optional.ofNullable(em.find(BookWithComments.class, id));
    }

    @Override
    public List<BookWithComments> getAllWithComments() {
        return em.createQuery("select distinct bc from BookWithComments bc join fetch bc.comments order by bc.id", BookWithComments.class).getResultList();
    }
}
