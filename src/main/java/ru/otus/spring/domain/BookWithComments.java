package ru.otus.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Document
public class BookWithComments {

    @Id
    @Field(value = "id")
    private long id;

    @Field(value = "book")
    private Book book;

    @Field(value = "comments")
    private List<Comment> comments;

}
