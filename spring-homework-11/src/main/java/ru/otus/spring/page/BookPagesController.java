package ru.otus.spring.page;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookPagesController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }
}
