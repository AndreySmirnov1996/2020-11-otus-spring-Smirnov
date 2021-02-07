package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.service.crud.CommentCrudService;

@ShellComponent
@RequiredArgsConstructor
public class CommentCrudShell {

    private final CommentCrudService commentCrudService;

    @ShellMethod(value = "Delete comment by id (example: dc 1)", key = {"dc", "delete comment"})
    public void deleteCommentById(@ShellOption long id) {
        //commentCrudService.deleteCommentById(id);
    }

    @ShellMethod(value = "Update text comment by id (example: uct 1 new_text)", key = {"uct", "update comment text"})
    public void updateCommentTextById(@ShellOption long id, @ShellOption String newText) {
        //commentCrudService.updateCommentTextById(id, newText);
    }

    @ShellMethod(value = "Show all comments (example: sac)", key = {"sac", "show all comments"})
    public void showAllComments() {
        commentCrudService.showAllComments();
    }

    @ShellMethod(value = "Show book by id (example: sbcid 1)", key = {"sbcid", "show book comments"})
    public void showCommentsByBookId(@ShellOption String bookId) {
        commentCrudService.showAllCommentsByBookId(bookId);
    }

    //For example:
    //sc comment_text_new 1
    @ShellMethod(value = "Save comment (example: sc comment_text_new 1)",
            key = {"sc", "save comment"})
    public void saveComment(@ShellOption String title, @ShellOption long bookId) {
        //commentCrudService.saveComment(title, bookId);
    }

}
