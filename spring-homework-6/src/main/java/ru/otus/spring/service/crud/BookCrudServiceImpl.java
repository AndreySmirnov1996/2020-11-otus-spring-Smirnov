package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.ObjectFactory;
import ru.otus.spring.service.OutputFormatter;

@Service
@RequiredArgsConstructor
public class BookCrudServiceImpl implements BookCrudService {

    private final BookRepository bookRepository;
    private final ObjectFactory objectFactory;
    private final OutputFormatter outputFormatter;
    private final IOService ioService;

    @Transactional
    @Override
    public void saveBook(String title, String genreName, String authors) {
        Book book = objectFactory.createBookEntity(title, genreName, authors);
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public void showBookById(long id) {
        bookRepository.findById(id).ifPresent(book -> ioService.printString(outputFormatter.formatBook(book)));
    }

    @Transactional(readOnly = true)
    @Override
    public void showAllBooks() {
        bookRepository.findAll().forEach(book -> ioService.printString(outputFormatter.formatBook(book)));
    }

    @Transactional
    @Override
    public void updateBookTitleById(long bookId, String newTitle) {
        bookRepository.updateTitleById(bookId, newTitle);
    }

    @Transactional
    @Override
    public void deleteBookById(long bookId) {
        bookRepository.delete(bookId);
    }
}
