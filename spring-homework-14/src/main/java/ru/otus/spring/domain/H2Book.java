package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Table(name = "books")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class H2Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "title")
    private String title;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(targetEntity = H2Genre.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private H2Genre h2Genre;

    @BatchSize(size = 5)
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity = H2Author.class, cascade = CascadeType.MERGE)
    @JoinTable(name = "authors_books", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<H2Author> h2Authors;
}
