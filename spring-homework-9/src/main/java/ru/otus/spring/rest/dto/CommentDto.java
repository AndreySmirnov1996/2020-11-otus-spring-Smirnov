package ru.otus.spring.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
@NoArgsConstructor
public class CommentDto {

    private long id;
    private String text;
    private long bookId;


    private CommentDto(Comment comment) {
        id = comment.getId();
        text = comment.getText();
        bookId = comment.getBook().getId();
    }

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment);
    }

    public Comment toComment() {
        return Comment.builder()
                .id(id)
                .text(text)
                .book(Book.builder().id(bookId).build())
                .build();
    }
}
