package ru.otus.spring.events;

import com.mongodb.MongoException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeSaveEventsListener extends AbstractMongoEventListener<Book> {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);
        val book = event.getSource();
        if(!genreRepository.existsById(book.getGenre().getId())){
            throw new MongoException("Genre is not found with id = " + book.getGenre().getId());

        }
        if (book.getAuthors() != null) {
            book.getAuthors().stream().filter(e -> Objects.isNull(e.getId())).forEach(authorRepository::save);
        }
    }
}
