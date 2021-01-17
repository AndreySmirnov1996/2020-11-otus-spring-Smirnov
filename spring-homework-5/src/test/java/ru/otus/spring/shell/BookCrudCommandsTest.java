package ru.otus.spring.shell;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.config.BeansConfig;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.AuthorRepositoryImpl;
import ru.otus.spring.repositories.BookRepositoryImpl;
import ru.otus.spring.repositories.BookRepositoryImpl.BookRowMapper;
import ru.otus.spring.repositories.GenreRepositoryImpl;
import ru.otus.spring.service.ObjectFactoryImpl;
import ru.otus.spring.service.OutputFormatterImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Shell команды на основе Jdbc для работы с книгами  ")
@JdbcTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Import({BookRepositoryImpl.class, BookCrudCommands.class,
        BeansConfig.class, ObjectFactoryImpl.class, AuthorRepositoryImpl.class})
@MockBeans({
        @MockBean(OutputFormatterImpl.class),
        @MockBean(GenreRepositoryImpl.class)
})
class BookCrudCommandsTest {

    private static final String FIND_BOOK_BY_ID = "select * from books b join genres g on b.genre_id=g.id where b.id=:id";

    @Autowired
    private BookCrudCommands bookCrudCommands;
    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @DisplayName("должен сохранять книгу")
    @Test
    void saveBookTest() {
        val bookId = 7L;
        val bookTitle = "book_title_new";

        bookCrudCommands.saveBook(bookId, bookTitle,1, "NONE", "1;5,Name1,Surname1");

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", bookId);
        List<Book> books = jdbc.query(FIND_BOOK_BY_ID, sqlParameterSource, new BookRowMapper());
        assertEquals(1, books.size());

        books.forEach(f -> {
            assertEquals(bookId, f.getId());
            assertEquals(bookTitle, f.getTitle());
            assertEquals(1, f.getGenre().getId());
            //Чтобы достать авторов нужно делать еще запросы через таблицу связей, поэтому нет проверки на авторов.
        });

    }

}