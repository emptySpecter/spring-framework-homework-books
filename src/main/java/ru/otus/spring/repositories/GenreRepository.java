package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public Optional<Genre> getById(long id) {
//        return Optional.ofNullable(em.find(Genre.class, id));
//    }
//
//    @Override
//    public List<Genre> getAll() {
//        return em.createQuery("select g from Genre g", Genre.class).getResultList();
//    }
}
