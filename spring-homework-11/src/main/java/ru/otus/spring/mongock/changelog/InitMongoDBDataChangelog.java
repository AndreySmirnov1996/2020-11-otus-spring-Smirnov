package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.util.Collections;

@ChangeLog(order = "001")
public class InitMongoDBDataChangelog {

    private Mono<Genre> tragedyGenre;
    private Mono<Genre> romanGenre;
    private Mono<Author> williamShakespeareAuthor;
    private Mono<Author> leoTolstoyAuthor;
    private Mono<Book> romeoAndJulietBook;
    private Mono<Book> annaKareninaBook;

    @ChangeSet(order = "000", id = "dropDb", author = "assmirnov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "001", id = "initGenres", author = "assmirnov", runAlways = true)
    public void initGenres(GenreRepository repository) {
        tragedyGenre = repository.save(new Genre("1", "Tragedy"));
        romanGenre = repository.save(new Genre("2", "Roman"));
        repository.save(new Genre("3", "Detective fiction"));
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "assmirnov", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        williamShakespeareAuthor = repository.save(new Author("111", "William", "Shakespeare"));
        leoTolstoyAuthor = repository.save(new Author("222", "Leo", "Tolstoy"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "assmirnov", runAlways = true)
    public void initBooks(BookRepository repository) {
        romeoAndJulietBook = repository.save(new Book("Romeo and Juliet", tragedyGenre.block(),
                Collections.singletonList(williamShakespeareAuthor.block())));
        annaKareninaBook = repository.save(new Book("Anna Karenina", romanGenre.block(),
                Collections.singletonList(leoTolstoyAuthor.block())));
    }

    @ChangeSet(order = "004", id = "initComments", author = "assmirnov", runAlways = true)
    public void initComments(CommentRepository repository) {
        repository.save(new Comment("The best tragedy!", romeoAndJulietBook.block()));
        repository.save(new Comment("The best roman!!!", annaKareninaBook.block()));
    }
}
