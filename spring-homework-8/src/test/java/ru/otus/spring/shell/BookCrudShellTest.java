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
import org.springframework.test.context.TestPropertySource;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Shell команды для работы с книгами должны ")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@TestPropertySource(properties = "mongock.enabled: false")
@Import(BookCrudShell.class)
class BookCrudShellTest {

    @MockBean
    private BookRepository bookRepository;
    @Captor
    private ArgumentCaptor<Book> bookCaptor;
    @Autowired
    private BookCrudShell bookCrudShell;

    @DisplayName("сохранять книгу")
    @Test
    void saveBookTest() {
        val bookTitle = "book_title_new";
        val genre = new Genre("11122");
        val authorsListSize = 2;
        bookCrudShell.saveBook(bookTitle, genre.getId(), "1;5,Name1,Surname1");

        Mockito.verify(bookRepository).save(bookCaptor.capture());
        Book bookCaptureValue = bookCaptor.getValue();

        assertEquals(bookTitle, bookCaptureValue.getTitle());
        assertEquals(genre, bookCaptureValue.getGenre());
        assertEquals(authorsListSize, bookCaptureValue.getAuthors().size());
    }

}