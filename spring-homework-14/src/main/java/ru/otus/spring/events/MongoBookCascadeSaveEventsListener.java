package ru.otus.spring.events;

import com.mongodb.MongoException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.MongoBook;
import ru.otus.spring.repositories.MongoAuthorRepository;
import ru.otus.spring.repositories.MongoGenreRepository;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeSaveEventsListener extends AbstractMongoEventListener<MongoBook> {

    private final MongoAuthorRepository mongoAuthorRepository;
    private final MongoGenreRepository mongoGenreRepository;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<MongoBook> event) {
        super.onBeforeConvert(event);
        val book = event.getSource();
        if (book.getMongoGenre() != null && !mongoGenreRepository.existsById(book.getMongoGenre().getId())) {
            throw new MongoException("Genre is not found with id = " + book.getMongoGenre().getId());
        }
        if (book.getMongoAuthors() != null) {
            book.getMongoAuthors().stream().filter(author -> author.getName() != null && author.getSurname() != null)
                    .forEach(mongoAuthorRepository::save);
        }
    }
}
