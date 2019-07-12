package ru.otus.spring.domain.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    long id;
    String name;
    long pagecount;
    long points;
    Genre genre;
    Author author;
}
