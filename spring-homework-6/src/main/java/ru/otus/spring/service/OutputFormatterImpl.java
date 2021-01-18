package ru.otus.spring.service;


import org.springframework.stereotype.Service;
import ru.otus.spring.domain.AuthorEntity;
import ru.otus.spring.domain.BookEntity;
import ru.otus.spring.domain.GenreEntity;

import java.util.List;

@Service
public class OutputFormatterImpl implements OutputFormatter {

    @Override
    public String formatBook(BookEntity book) {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(book.getId());
        sb.append("\ttitle: ").append(book.getTitle());
        sb.append("\tgenre_id: ").append(book.getGenre().getId());
        sb.append("\tauthors: ");

        List<AuthorEntity> authorsList = book.getAuthors();
        authorsList.forEach(f -> sb.append(f.getName()).append(" ").append(f.getSurname()).append(", "));
        sb.delete(sb.length() - 2, sb.length() - 1);

        return sb.append("\n").toString();
    }

    @Override
    public String formatGenre(GenreEntity genre) {
        return "Id: " + genre.getId() + "\tName: " + genre.getName() + "\n";
    }
}
