package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
