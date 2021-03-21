package ru.otus.spring.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.service.crud.BookCrudService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("REST Контроллер для книг должен")
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookCrudService service;

    @DisplayName("отображать главную страницу со списком книг")
    @Test
    void indexPageTest() throws Exception {
        Author author1 = new Author(11, "author_name_1", "author_surname_1");
        Author author2 = new Author(22, "author_name_2", "author_surname_2");
        Book book = createBook(11, "book_name_1", new Genre(11, "genre_1"),
                Arrays.asList(author1, author2));

        given(service.findAll()).willReturn(Collections.singletonList(book));

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/"));

        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("books"));

        MvcResult mvcResult = result.andReturn();
        List<BookDto> bookDtoList = (List<BookDto>) mvcResult.getModelAndView().getModel().get("books");
        assertEquals(bookDtoList.size(), 1);
    }

    @DisplayName("открывать форму для ввода новой книги")
    @Test
    void newBookPageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/new"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("new_book"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("book"));
    }

    @DisplayName("удалять книгу")
    @Test
    void deleteBookTest() throws Exception {
        long bookId = 1;
        Mockito.doNothing().when(service).deleteBookById(bookId);
        mockMvc.perform(MockMvcRequestBuilders.post("/book/delete").param("id", String.valueOf(bookId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        verify(service, times(1)).deleteBookById(bookId);
    }

    @DisplayName("открывать форму редактирования книги")
    @Test
    void editBookPageTest() throws Exception {
        long bookId = 1;

        Author author1 = new Author(11, "author_name_1", "author_surname_1");
        Author author2 = new Author(22, "author_name_2", "author_surname_2");
        Book book = createBook(bookId, "book_name_1", new Genre(11, "genre_1"),
                Arrays.asList(author1, author2));

        given(service.findById(bookId)).willReturn(ofNullable(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/{id}/edit", String.valueOf(bookId)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("edit_book"));

        verify(service, times(1)).findById(bookId);
    }

    private Book createBook(long id, String bookTitle, Genre genre, List<Author> authors) {
        return Book.builder()
                .id(id)
                .title(bookTitle)
                .genre(genre)
                .authors(authors)
                .build();
    }
}