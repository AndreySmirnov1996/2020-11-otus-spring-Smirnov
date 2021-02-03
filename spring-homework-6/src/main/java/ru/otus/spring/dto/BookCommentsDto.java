package ru.otus.spring.dto;

import lombok.RequiredArgsConstructor;

import javax.xml.stream.events.Comment;
import java.awt.print.Book;
import java.util.List;

@RequiredArgsConstructor
public class BookCommentsDto {
    private final Book book;
    private final List<Comment> comments;
}
