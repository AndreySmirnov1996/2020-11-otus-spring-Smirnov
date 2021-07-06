package ru.otus.spring.service;


import org.springframework.stereotype.Service;
import ru.otus.spring.domain.MongoAuthor;
import ru.otus.spring.domain.MongoBook;
import ru.otus.spring.domain.MongoGenre;

import java.util.List;

@Service
public class OutputFormatterImpl implements OutputFormatter {

    @Override
    public String formatBook(MongoBook mongoBook) {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(mongoBook.getId());
        sb.append("\ttitle: ").append(mongoBook.getTitle());
        sb.append("\tgenre_id: ").append(mongoBook.getMongoGenre().getId());
        sb.append("\tauthors: ");

        List<MongoAuthor> authorsList = mongoBook.getMongoAuthors();
        authorsList.forEach(f -> sb.append(f.getName()).append(" ").append(f.getSurname()).append(", "));
        sb.delete(sb.length() - 2, sb.length() - 1);

        return sb.append("\n").toString();
    }

    @Override
    public String formatGenre(MongoGenre mongoGenre) {
        return "Id: " + mongoGenre.getId() + "\tName: " + mongoGenre.getName() + "\n";
    }

}
