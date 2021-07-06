package ru.otus.spring.mongock.testchangelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.domain.MongoAuthor;
import ru.otus.spring.domain.MongoBook;
import ru.otus.spring.domain.MongoGenre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.util.Collections;

@ChangeLog(order = "001")
public class InitMongoDBTestDataChangelog {

    private MongoGenre tragedyMongoGenre;
    private MongoAuthor williamShakespeareMongoAuthor;
    private MongoBook romeoAndJulietMongoBook;

    @ChangeSet(order = "000", id = "dropDb", author = "assmirnov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "001", id = "initGenres", author = "assmirnov", runAlways = true)
    public void initGenres(GenreRepository repository) {
        tragedyMongoGenre = repository.save(new MongoGenre("1", "Tragedy"));
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "assmirnov", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        williamShakespeareMongoAuthor = repository.save(new MongoAuthor("111", "William", "Shakespeare"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "assmirnov", runAlways = true)
    public void initBooks(BookRepository repository) {
        romeoAndJulietMongoBook = repository.save(new MongoBook("1234", "Romeo and Juliet", tragedyMongoGenre, Collections.singletonList(williamShakespeareMongoAuthor)));
    }
}
