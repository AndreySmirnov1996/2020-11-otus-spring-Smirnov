package ru.otus.spring.page;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentPagesController {

    private final CommentCrudService commentCrudService;

    @GetMapping("/book/{id}/comment")
    public String listComments(@PathVariable("id") String id, Model model) {
        List<Comment> commentDto = commentCrudService.findAllByBookId(id).collectList().block();
        model.addAttribute("comments", commentDto);
        model.addAttribute("bookId", id);
        return "comments";
    }

    @GetMapping("/book/{id}/comment/{commentId}/edit")
    public String editComment(@PathVariable("commentId") String commentId, Model model) {
        Comment comment = commentCrudService.findById(commentId).block();
        model.addAttribute("comment", comment);
        return "edit_comment";
    }

    @GetMapping("/book/{id}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable String commentId) {
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

    // Форма для создания нового комментария
    @GetMapping("/book/{id}/comment/new")
    public String newCommentForm(@PathVariable String id, Map<String, Object> model) {
        CommentDto comment = new CommentDto();
        comment.setBookId(id);
        model.put("comment", comment);
        return "new_comment";
    }

    @PostMapping(value = "/comment/save")
    public String saveComment(@ModelAttribute("comment") CommentDto comment, Map<String, Object> model) {
        try {
            commentCrudService.saveComment(comment.toComment());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            model.put("message", "Failed: " + ex.getMessage());
            return "new_comment";
        }
        return "redirect:/book/" + comment.getBookId() + "/comment";
    }

}
