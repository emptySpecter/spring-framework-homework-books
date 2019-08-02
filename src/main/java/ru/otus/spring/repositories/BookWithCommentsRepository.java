package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.spring.domain.BookWithComments;

import java.util.List;

public interface BookWithCommentsRepository extends JpaRepository<BookWithComments, Long> {
    @Override
    @Query("select distinct bc from BookWithComments bc right join fetch bc.comments order by bc.id")
    List<BookWithComments> findAll();

}
