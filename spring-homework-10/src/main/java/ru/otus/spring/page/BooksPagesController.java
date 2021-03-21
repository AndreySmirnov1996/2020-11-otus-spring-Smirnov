package ru.otus.spring.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.domain.Book;
import ru.otus.spring.rest.dto.BookDto;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BooksPagesController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }
}
