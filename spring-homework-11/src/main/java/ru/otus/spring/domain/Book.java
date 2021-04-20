package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String title;
    @DBRef
    private Genre genre;
    @DBRef
    private List<Author> authors;

    public Book(String title, Genre genre, List<Author> authors) {
        this.title = title;
        this.genre = genre;
        this.authors = authors;
    }
}
