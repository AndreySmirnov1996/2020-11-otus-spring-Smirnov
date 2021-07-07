package ru.otus.spring.service.crud;

import com.mongodb.MongoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.MongoBook;
import ru.otus.spring.repositories.MongoBookRepository;
import ru.otus.spring.service.ObjectFactory;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCrudServiceImpl implements BookCrudService {

    private final MongoBookRepository mongoBookRepository;
    private final ObjectFactory objectFactory;

    @Transactional
    @Override
    public void saveBook(String title, String genreId, String authors) {
        MongoBook mongoBook = objectFactory.createBookEntity(title, genreId, authors);
        mongoBookRepository.save(mongoBook);
    }

    @Transactional
    @Override
    public void updateBookTitleById(String bookId, String newTitle) {
        Optional<MongoBook> bookOptional = mongoBookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            MongoBook newMongoBook = bookOptional.get();
            newMongoBook.setTitle(newTitle);
            mongoBookRepository.save(newMongoBook);
        } else {
            throw new MongoException("Book doesn't exist with id = " + bookId);
        }
    }

    @Transactional
    @Override
    public void deleteBookById(String bookId) {
        mongoBookRepository.deleteById(bookId);
    }
}
