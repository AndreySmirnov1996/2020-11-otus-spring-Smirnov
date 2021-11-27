package ru.otus.spring.service.crud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCrudServiceImpl implements BookCrudService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public void save(Book book) {
        book.getAuthors().forEach(author -> {
            if (author.getId() <= 0) {
                authorRepository.save(author);
            }
        });
        bookRepository.save(book);
    }

    @HystrixCommand(commandKey = "books", fallbackMethod = "findByIdFallback")
    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(long id) {
        //ThreadUtils.sleepRandomly(5000);
        return bookRepository.findById(id);
    }

    @HystrixCommand(commandKey = "books", fallbackMethod = "findAllFallback")
    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        //ThreadUtils.sleepRandomly(5000);
        return bookRepository.findAllWithAllInfo();
    }

    @Transactional
    @Override
    public void updateBookTitleById(long bookId, String newTitle) {
        bookRepository.updateTitleById(bookId, newTitle);
    }

    // TODO Doesn't work. why?
    @HystrixCommand(commandKey = "books", fallbackMethod = "deleteBookByIdFallback")
    //@Transactional
    @Override
    public boolean deleteBookById(long bookId) {
        ThreadUtils.sleepRandomly(10000);
//        commentRepository.deleteAllByBookId(bookId);
        bookRepository.deleteById(bookId);
        return true;
    }

    public List<Book> findAllFallback() {
        return new ArrayList<>();
    }

    public Optional<Book> findByIdFallback(long id) {
        return Optional.empty();
    }

    public boolean deleteBookByIdFallback(long id) {
        return false;
    }

}
