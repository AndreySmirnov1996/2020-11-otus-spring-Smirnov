package ru.otus.spring.events;

import com.mongodb.MongoException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.MongoBook;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.GenreRepository;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeSaveEventsListener extends AbstractMongoEventListener<MongoBook> {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<MongoBook> event) {
        super.onBeforeConvert(event);
        val book = event.getSource();
        if (book.getMongoGenre() != null && !genreRepository.existsById(book.getMongoGenre().getId())) {
            throw new MongoException("Genre is not found with id = " + book.getMongoGenre().getId());
        }
        if (book.getMongoAuthors() != null) {
            book.getMongoAuthors().stream().filter(author -> author.getName() != null && author.getSurname() != null)
                    .forEach(authorRepository::save);
        }
    }
}
