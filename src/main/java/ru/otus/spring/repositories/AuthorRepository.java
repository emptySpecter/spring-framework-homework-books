package ru.otus.spring.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, ObjectId> {

}
