package ru.otus.spring.service.crud;

import com.mongodb.MongoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.MongoBook;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.ObjectFactory;
import ru.otus.spring.service.OutputFormatter;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCrudServiceImpl implements BookCrudService {

    private final BookRepository bookRepository;
    private final ObjectFactory objectFactory;
    private final OutputFormatter outputFormatter;
    private final IOService ioService;

    @Transactional
    @Override
    public void saveBook(String title, String genreId, String authors) {
        MongoBook mongoBook = objectFactory.createBookEntity(title, genreId, authors);
        bookRepository.save(mongoBook);
    }

    @Transactional(readOnly = true)
    @Override
    public void showBookById(String id) {
        bookRepository.findById(id).ifPresent(book -> ioService.printString(outputFormatter.formatBook(book)));
    }

    @Transactional(readOnly = true)
    @Override
    public void showAllBooks() {
        bookRepository.findAll().forEach(book -> ioService.printString(outputFormatter.formatBook(book)));
    }

    @Transactional
    @Override
    public void updateBookTitleById(String bookId, String newTitle) {
        Optional<MongoBook> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            MongoBook newMongoBook = bookOptional.get();
            newMongoBook.setTitle(newTitle);
            bookRepository.save(newMongoBook);
        } else {
            throw new MongoException("Book doesn't exist with id = " + bookId);
        }
    }

    @Transactional
    @Override
    public void deleteBookById(String bookId) {
        bookRepository.deleteById(bookId);
    }
}