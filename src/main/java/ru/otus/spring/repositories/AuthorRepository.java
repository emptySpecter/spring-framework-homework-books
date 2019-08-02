package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
