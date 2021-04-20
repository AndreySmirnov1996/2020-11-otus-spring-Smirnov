package ru.otus.spring.service.crud;

import com.mongodb.MongoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCrudServiceImpl implements BookCrudService {

    private final CommentCrudService commentCrudService;
    private final BookRepository bookRepository;

    @Transactional
    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public void updateBookTitleById(String bookId, String newTitle) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            Book newBook = bookOptional.get();
            newBook.setTitle(newTitle);
            bookRepository.save(newBook);
        } else {
            throw new MongoException("Book doesn't exist with id = " + bookId);
        }
    }

    @Transactional
    @Override
    public void deleteBookById(String bookId) {
        commentCrudService.deleteAllCommentsByBookId(bookId);
        bookRepository.deleteById(bookId);
    }
}
