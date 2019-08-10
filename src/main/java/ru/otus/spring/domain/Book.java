package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    @Field(value = "name")
    private String name;
    @Field(value = "pagecount")
    private long pagecount;
    @Field(value = "points")
    private long points;
    @Field(value = "genre")
    private Genre genre;
    @Field(value = "author")
    private Author author;
    @Field(value = "comments")
    private List<Comment> comments;
}
