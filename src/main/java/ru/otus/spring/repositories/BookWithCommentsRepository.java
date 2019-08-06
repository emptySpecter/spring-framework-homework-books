package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.BookWithComments;

import java.util.List;

public interface BookWithCommentsRepository extends MongoRepository<BookWithComments, Long> {
    @Override
//    @Query("select distinct bc from BookWithComments bc right join fetch bc.comments order by bc.id")
    List<BookWithComments> findAll();

}
