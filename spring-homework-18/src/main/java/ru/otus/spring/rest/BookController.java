package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.spring.domain.Book;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.service.crud.BookCrudService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookCrudService bookCrudService;

    @GetMapping("/")
    public String indexPage(Map<String, Object> model) {
        List<Book> books = bookCrudService.findAll();
        // model.put("message", "Can not open edit page. Please try again");
        log.info("777777777777777");
        List<BookDto> booksDto = books.stream().map(BookDto::toDto).collect(Collectors.toList());
        model.put("books", booksDto);
        return "index";
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
    public ModelAndView editBook(@PathVariable("id") long id, Map<String, Object> model) {
        Optional<Book> bookOptional = bookCrudService.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            model.put("book", BookDto.toDto(book));
            return new ModelAndView("edit_book");
        }
        model.put("message", "Can not open edit page. Please try again");
        return new ModelAndView("redirect:/", model);
    }


    @PostMapping("/book/delete")
    public String deleteBook(@RequestParam long id) {
        bookCrudService.deleteBookById(id);
        return "redirect:/";
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
        return "redirect:/";
    }

    @PostMapping(value = "/book/edit")
    public String editBook(@ModelAttribute("book") BookDto book) {
        bookCrudService.updateBookTitleById(book.getId(), book.getTitle());
        return "redirect:/";
    }
}
