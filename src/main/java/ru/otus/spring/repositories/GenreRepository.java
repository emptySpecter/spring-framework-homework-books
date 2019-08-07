package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
