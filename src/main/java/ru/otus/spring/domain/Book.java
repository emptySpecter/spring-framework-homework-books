package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId", nullable = false, unique = true)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "pagecount", nullable = false)
    private long pagecount;
    @Column(name = "points", nullable = false)
    private long points;
    @OneToOne(targetEntity = Genre.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "genreId")
    private Genre genre;
    @OneToOne(targetEntity = Author.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "authorId")
    private Author author;
}
