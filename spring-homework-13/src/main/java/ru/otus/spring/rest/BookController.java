package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Book;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.service.crud.BookCrudService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookCrudService bookCrudService;

    @PostMapping("/book")
    public String postPage(Model model) {
        List<Book> books = bookCrudService.findAll();
        List<BookDto> booksDto = books.stream().map(BookDto::toDto).collect(Collectors.toList());
        model.addAttribute("books", booksDto);
        return "book";
    }

    @GetMapping("/book")
    public String getIndex(Model model) {
        List<Book> books = bookCrudService.findAll();
        List<BookDto> booksDto = books.stream().map(BookDto::toDto).collect(Collectors.toList());
        model.addAttribute("books", booksDto);
        return "book";
    }

    // Форма для создания новой книги
    @GetMapping("/book/new")
    public String newBookForm(Map<String, Object> model) {
        BookDto book = new BookDto();
        model.put("book", book);
        return "new_book";
    }

    // Форма для редактирования книги
    @GetMapping("/book/{id}/edit")
    public String editBook(@PathVariable("id") long id, Model model) throws NotFoundException {
        Book book = bookCrudService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", BookDto.toDto(book));
        return "edit_book";
    }


    @PostMapping("/book/delete")
    public String deleteBook(@RequestParam long id) {
        bookCrudService.deleteBookById(id);
        return "redirect:/book";
    }

    @PostMapping(value = "/book/save")
    public String saveBook(@ModelAttribute("book") BookDto book, Map<String, Object> model) {
        try {
            bookCrudService.save(book.toBook());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            model.put("message", "Failed: " + ex.getMessage());
            return "new_book";
        }
        return "redirect:/book";
    }

    @PostMapping(value = "/book/edit")
    public String editBook(@ModelAttribute("book") BookDto book) {
        bookCrudService.updateBookTitleById(book.getId(), book.getTitle());
        return "redirect:/book";
    }
}
