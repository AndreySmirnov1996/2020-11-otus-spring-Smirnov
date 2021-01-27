package ru.otus.spring.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.repositories.BookRepositoryImpl;

@DisplayName("Shell команды на основе Jdbc для работы с книгами  ")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Import(BookCrudCommands.class)
class BookCrudCommandsTest {

    @MockBean
    private BookRepositoryImpl bookRepository;
//    @Captor
//    private ArgumentCaptor<Book> bookCaptor;
    @Autowired
    private BookCrudCommands bookCrudCommands;

    @DisplayName("должен сохранять книгу")
    @Test
    void saveBookTest() {
//        val bookTitle = "book_title_new";
//        val genre = new Genre(1);
//        val authorsListSize = 2;
//        bookCrudCommands.createBook(bookTitle, "1", "1;5,Name1,Surname1");
//
//        Mockito.verify(bookRepository).save(bookCaptor.capture());
//        Book bookCaptureValue = bookCaptor.getValue();
//
//        assertEquals(bookTitle, bookCaptureValue.getTitle());
//        assertEquals(genre, bookCaptureValue.getGenre());
//        assertEquals(authorsListSize, bookCaptureValue.getAuthors().size());
    }

}