package ru.otus.spring.mongock.testchangelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.util.Collections;
import java.util.List;

@ChangeLog(order = "001")
public class InitMongoDBTestDataChangelog {

    private Genre tragedyGenre;
    private Author williamShakespeareAuthor;
    private Book romeoAndJulietBook;

    @ChangeSet(order = "000", id = "dropDb", author = "assmirnov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "001", id = "initGenres", author = "assmirnov", runAlways = true)
    public void initGenres(GenreRepository repository) {
        tragedyGenre = repository.save(new Genre("1", "Tragedy"));
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "assmirnov", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        williamShakespeareAuthor = repository.save(new Author("111", "William", "Shakespeare"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "assmirnov", runAlways = true)
    public void initBooks(BookRepository repository) {
        romeoAndJulietBook = repository.save(new Book("1234", "Romeo and Juliet", tragedyGenre, Collections.singletonList(williamShakespeareAuthor)));
    }

    @ChangeSet(order = "004", id = "initComments", author = "assmirnov", runAlways = true)
    public void initComments(CommentRepository repository) {
        repository.save(new Comment("111", "The best tragedy!", romeoAndJulietBook));
        repository.save(new Comment("222", "Not bad, but everyone died", romeoAndJulietBook));
    }
}
