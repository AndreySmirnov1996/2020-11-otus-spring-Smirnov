package ru.otus.spring.shell;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Shell команды на основе JPA для работы с книгами  ")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Import(BookCrudService.class)
class BookCrudServiceTest {

    @MockBean
    private BookRepository bookRepository;
    @Captor
    private ArgumentCaptor<Book> bookCaptor;
    @Autowired
    private BookCrudService bookCrudService;

    @DisplayName("должен сохранять книгу")
    @Test
    void saveBookTest() {
        val bookTitle = "book_title_new";
        val genre = new Genre();
        genre.setName("genre_new");
        val authorsListSize = 2;
        bookCrudService.saveBook(bookTitle, genre.getName(), "1;5,Name1,Surname1");

        Mockito.verify(bookRepository).save(bookCaptor.capture());
        Book bookCaptureValue = bookCaptor.getValue();

        assertEquals(bookTitle, bookCaptureValue.getTitle());
        assertEquals(genre, bookCaptureValue.getGenre());
        assertEquals(authorsListSize, bookCaptureValue.getAuthors().size());
    }

}