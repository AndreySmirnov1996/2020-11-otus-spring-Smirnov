package ru.otus.spring.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BooksPagesController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }


    // Форма для редактирования книги
    @GetMapping("/book/{id}/edit")
    public String editBook() {
        return "edit_book";
    }
}
