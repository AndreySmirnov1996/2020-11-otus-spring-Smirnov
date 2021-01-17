package ru.otus.spring.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phone")
    private String phone;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY /*, cascade = CascadeType.ALL*/)
    @JoinTable(name = "authors_books", joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;
}
