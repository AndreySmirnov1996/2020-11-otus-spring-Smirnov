package ru.otus.spring.page;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Book;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.service.crud.BookCrudService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookPagesController {

    private final BookCrudService bookCrudService;

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @PostMapping("/book/delete")
    public String deleteBookOld(@RequestParam String id) {
        bookCrudService.deleteBookById(id);
        return "redirect:/";
    }

    // Форма для редактирования книги
    @GetMapping("/book/{id}/edit")
    public String editBook(@PathVariable("id") String id, Model model) throws NotFoundException {
        Book book = bookCrudService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", BookDto.toDto(book));
        return "edit_book";
    }

    @PostMapping(value = "/book/edit")
    public String editBook(@ModelAttribute("book") BookDto book) {
        bookCrudService.updateBookTitleById(book.getId(), book.getTitle());
        return "redirect:/";
    }
}
