package ru.otus.spring.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;
    @Column(name = "cost")
    private String cost;

    @OneToOne(targetEntity = Genre.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY /*, cascade = CascadeType.ALL*/)
    @JoinTable(name = "authors_books", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;
}
