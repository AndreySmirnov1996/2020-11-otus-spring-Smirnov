package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
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

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookCrudService bookCrudService;

    @GetMapping("/")
    public String listPage(Model model) {
        List<BookDto> booksDto = bookCrudService.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
        model.addAttribute("books", booksDto);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) throws NotFoundException {
        Book book = bookCrudService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit")
    public String saveBook(Book book, Model model) {
        //bookCrudService.save(book);
        List<BookDto> booksDto = bookCrudService.findAll()
                .stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
        model.addAttribute("books", booksDto);
        return "redirect:/";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteCustomerForm(@PathVariable long id) {
        bookCrudService.deleteBookById(id);
        return "redirect:/";
    }

    @PostMapping("/new")
    public String newCustomerForm(Map<String, Object> model) {
        Book book = new Book();
        model.put("book", book);
        return "new_book";
    }

    @PostMapping(value = "/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookCrudService.save(book);
        return "redirect:/";
    }
}
