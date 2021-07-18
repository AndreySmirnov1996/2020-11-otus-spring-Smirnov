package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.domain.MongoAuthor;
import ru.otus.spring.domain.MongoBook;
import ru.otus.spring.domain.MongoGenre;
import ru.otus.spring.repositories.MongoAuthorRepository;
import ru.otus.spring.repositories.MongoBookRepository;
import ru.otus.spring.repositories.MongoGenreRepository;

@ChangeLog(order = "001")
public class InitMongoDBDataChangelog {

    private MongoGenre tragedyMongoGenre;
    private MongoGenre romanMongoGenre;
    private MongoGenre superGenre;
    private MongoAuthor williamShakespeareMongoAuthor;
    private MongoAuthor leoTolstoyMongoAuthor;

    @ChangeSet(order = "000", id = "dropDb", author = "assmirnov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "001", id = "initGenres", author = "assmirnov", runAlways = true)
    public void initGenres(MongoGenreRepository repository) {
        tragedyMongoGenre = repository.save(new MongoGenre("1qqq", "Tragedy"));
        romanMongoGenre = repository.save(new MongoGenre("2", "Roman"));
        superGenre = repository.save(new MongoGenre("3", "Romagedy"));
        repository.save(new MongoGenre("3", "Detective fiction"));
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "assmirnov", runAlways = true)
    public void initAuthors(MongoAuthorRepository repository) {
        williamShakespeareMongoAuthor = repository.save(new MongoAuthor("111qqq", "William", "Shakespeare"));
        leoTolstoyMongoAuthor = repository.save(new MongoAuthor("222", "Leo", "Tolstoy"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "assmirnov", runAlways = true)
    public void initBooks(MongoBookRepository repository) {
        repository.save(new MongoBook("Romeo and Juliet", tragedyMongoGenre, williamShakespeareMongoAuthor));
        repository.save(new MongoBook("Anna Karenina", romanMongoGenre, leoTolstoyMongoAuthor));
        repository.save(new MongoBook("Romeo and Anna Karenina (fake)", superGenre,
                williamShakespeareMongoAuthor, leoTolstoyMongoAuthor));
    }
}
