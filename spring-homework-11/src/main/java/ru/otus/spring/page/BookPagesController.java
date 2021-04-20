package ru.otus.spring.page;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.rest.dto.BookDto;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookPagesController {

    private final BookRepository bookRepository;

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @PostMapping("/book/delete")
    public String deleteBookOld(@RequestParam String id) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }

    // Форма для редактирования книги
    @GetMapping("/book/{id}/edit")
    public String editBook(@PathVariable("id") String id, Model model) {
        Book book = bookRepository.findById(id).block();
        model.addAttribute("book", BookDto.toDto(book));
        return "edit_book";
    }

//    @PostMapping(value = "/book/edit")
//    public String editBook(@ModelAttribute("book") BookDto book) {
//
//        bookRepository.updateBookTitleById(book.getId(), book.getTitle());
//        return "redirect:/";
//    }
}
