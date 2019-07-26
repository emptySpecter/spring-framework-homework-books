package ru.otus.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@Entity
@Table(name = "books")
@Getter
public class BookWithComments {

    @Id
    @Column(name = "bookid", nullable = false, unique = true)
    private long id;

    @OneToOne(targetEntity = Book.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bookid")
    private Book book;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bookid")
    private List<Comment> comments;

}
