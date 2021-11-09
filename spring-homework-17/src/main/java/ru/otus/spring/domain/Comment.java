package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Table(name = "comments")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @SequenceGenerator(name = "commentsSequence", sequenceName = "COMMENTS_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentsSequence")
    private long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
}