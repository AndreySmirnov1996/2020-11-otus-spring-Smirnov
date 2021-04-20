package ru.otus.spring.rest;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.page.BookPagesController;
import ru.otus.spring.service.crud.BookCrudService;

import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("REST Контроллер для книг должен")
@WebMvcTest(BookPagesController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookCrudService service;

    @DisplayName("удалять книгу")
    @Test
    void deleteBookTest() throws Exception {
        val bookId = "1";
        Mockito.doNothing().when(service).deleteBookById(bookId);
        mockMvc.perform(MockMvcRequestBuilders.post("/book/delete").param("id", bookId))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        verify(service, times(1)).deleteBookById(bookId);
    }

    @DisplayName("открывать форму редактирования книги")
    @Test
    void editBookPageTest() throws Exception {
        val bookId = "1";

        Author author1 = new Author("11", "author_name_1", "author_surname_1");
        Author author2 = new Author("22", "author_name_2", "author_surname_2");
        Book book = createBook(bookId, "book_name_1", new Genre("11", "genre_1"),
                Arrays.asList(author1, author2));

        given(service.findById(bookId)).willReturn(ofNullable(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/{id}/edit", String.valueOf(bookId)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("edit_book"));

        verify(service, times(1)).findById(bookId);
    }

    private Book createBook(String id, String bookTitle, Genre genre, List<Author> authors) {
        return Book.builder()
                .id(id)
                .title(bookTitle)
                .genre(genre)
                .authors(authors)
                .build();
    }
}