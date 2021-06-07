package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.BookRepository;

import java.util.Collections;

@ChangeLog(order = "001")
public class InitMongoDBDataChangelog {

    @ChangeSet(order = "000", id = "dropDb", author = "assmirnov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "003", id = "initBooks", author = "assmirnov", runAlways = true)
    public void initBooks(BookRepository repository) {
        repository.save(new Book("Romeo and Juliet", "Tragedy",
                Collections.singletonList(Author.builder().name("William").surname("Shakespeare").build())))
                .block();
        repository.save(new Book("Anna Karenina", "Roman",
                Collections.singletonList(Author.builder().name("Leo").surname("Tolstoy").build()))).block();
    }
}
