package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;

@ChangeLog(order = "001")
public class InitMongoDBDataChangelog {

    private Genre tragedyGenre;
    private Genre romanGenre;
    private Author williamShakespeareAuthor;
    private Author leoTolstoyAuthor;

    @ChangeSet(order = "000", id = "dropDb", author = "assmirnov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "001", id = "initGenres", author = "assmirnov", runAlways = true)
    public void initGenres(GenreRepository repository) {
        tragedyGenre = repository.save(new Genre("1", "Tragedy"));
        romanGenre = repository.save(new Genre("2", "Roman"));
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "assmirnov", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        williamShakespeareAuthor = repository.save(new Author("111", "William", "Shakespeare"));
        leoTolstoyAuthor = repository.save(new Author("222", "Leo", "Tolstoy"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "assmirnov", runAlways = true)
    public void initBooks(BookRepository repository) {
        repository.save(new Book("Romeo and Juliet", tragedyGenre, williamShakespeareAuthor));
        repository.save(new Book("Anna Karenina", romanGenre, leoTolstoyAuthor));
    }
}
