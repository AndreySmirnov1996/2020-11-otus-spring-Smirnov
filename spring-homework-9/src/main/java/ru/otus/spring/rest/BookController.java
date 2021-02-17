package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.domain.Book;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.service.crud.BookCrudService;

import java.util.List;
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

//    @GetMapping("/edit")
//    public String editPage(@RequestParam("id") int id, Model model) {
//        Person person = repository.findById(id).orElseThrow(NotFoundException::new);
//        model.addAttribute("person", person);
//        return "edit";
//    }
//
//    @PostMapping("/edit")
//    public String savePerson(
//            Person person,
//            Model model
//                            ) {
//        Person saved = repository.save(person);
//        model.addAttribute(saved);
//        //Ошибка! Нужен редирект!
//        return "edit";
//    }
}
