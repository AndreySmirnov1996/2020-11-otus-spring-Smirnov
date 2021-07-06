package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectFactoryImpl implements ObjectFactory {
    @Override
    public MongoBook createBookEntity(String title, String genreId, String authors) {
        List<MongoAuthor> authorsList = createAuthors(authors);
        MongoGenre mongoGenre = new MongoGenre(genreId);

        return MongoBook.builder()
                .title(title)
                .mongoGenre(mongoGenre)
                .mongoAuthors(authorsList)
                .build();
    }

    @Override
    public List<MongoAuthor> createAuthors(String authors) {
        List<MongoAuthor> authorsList = new ArrayList<>();
        if (!authors.equals("NONE")) {
            String[] authorsArray = authors.split(";");
            for (String str : authorsArray) {
                String[] data = str.split(",");
                MongoAuthor mongoAuthor;
                if (data.length == 2) {
                    mongoAuthor = MongoAuthor.builder()
                            .name(data[0])
                            .surname(data[1])
                            .build();
                } else {
                    mongoAuthor = MongoAuthor.builder()
                            .id(data[0])
                            .build();
                }
                authorsList.add(mongoAuthor);
            }
        }
        return authorsList;
    }

    @Override
    public MongoGenre createGenreEntity(String genreID) {
        MongoGenre mongoGenre = new MongoGenre();
        mongoGenre.setId(genreID);
        return mongoGenre;
    }

}
