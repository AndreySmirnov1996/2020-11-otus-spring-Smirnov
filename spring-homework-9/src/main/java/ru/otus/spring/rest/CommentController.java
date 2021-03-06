package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.spring.rest.dto.CommentDto;
import ru.otus.spring.service.crud.CommentCrudService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentCrudService commentCrudService;

    @GetMapping("/book/{id}/comment")
    public String indexPage(@PathVariable("id") long id, Model model) {
        List<CommentDto> commentDto = commentCrudService.findAllCommentsByBookId(id).stream()
                .map(CommentDto::toDto)
                .collect(Collectors.toList());
        model.addAttribute("comments", commentDto);
        return "comments";
    }

}
