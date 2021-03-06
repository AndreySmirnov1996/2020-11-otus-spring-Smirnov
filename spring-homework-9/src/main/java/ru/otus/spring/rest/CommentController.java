package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.rest.dto.CommentDto;
import ru.otus.spring.service.crud.CommentCrudService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentCrudService commentCrudService;

    @GetMapping("/book/{id}/comment")
    public String listComments(@PathVariable("id") long id, Model model) {
        List<CommentDto> commentDto = commentCrudService.findAllCommentsByBookId(id).stream()
                .map(CommentDto::toDto)
                .collect(Collectors.toList());
        model.addAttribute("comments", commentDto);
        return "comments";
    }

    @GetMapping("/book/{id}/comment/{commentId}/edit")
    public String editComment(@PathVariable("commentId") long commentId, Model model) throws ChangeSetPersister.NotFoundException {
        Comment comment = commentCrudService.findCommentById(commentId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        model.addAttribute("comment", comment);
        return "edit_comment";
    }

    @GetMapping("/book/{id}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable long commentId) {
        commentCrudService.deleteCommentById(commentId);
        return "redirect:/book/{id}/comment";
    }

    @PostMapping("/comment/update")
    public String updateComment(@ModelAttribute("comment") CommentDto commentDto, Map<String, Object> model) {
        try {
            commentCrudService.updateCommentTextById(commentDto.getId(), commentDto.getText());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            model.put("message", "Failed: " + ex.getMessage());
        }
        return "redirect:/book/ " + commentDto.getBookId() + "/comment";
    }

}
