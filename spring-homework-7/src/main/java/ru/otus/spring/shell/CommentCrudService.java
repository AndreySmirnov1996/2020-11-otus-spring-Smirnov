package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.CommentEntity;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.ObjectFactory;
import ru.otus.spring.service.OutputFormatter;

@ShellComponent
@RequiredArgsConstructor
public class CommentCrudService {

    private final IOService ioService;
    private final OutputFormatter outputFormatter;
    private final CommentRepository commentRepository;
    private final ObjectFactory objectFactory;

    @ShellMethod(value = "Delete comment by id (example: dc 1)", key = {"dc", "delete comment"})
    @Transactional
    public void deleteBookById(@ShellOption long id) {
        commentRepository.deleteById(id);
    }

    @ShellMethod(value = "Update text comment by id (example: ub 1 new_text)", key = {"uct", "update comment text"})
    @Transactional
    public void updateCommentTextById(@ShellOption long id, @ShellOption String newText) {
        commentRepository.updateTextById(id, newText);
    }

    @ShellMethod(value = "Show all comments (example: sac)", key = {"sac", "show all comments"})
    @Transactional(readOnly = true)
    public void showAllComments() {
        commentRepository.findAll().forEach(comment -> ioService.printString(outputFormatter.formatComment(comment)));
    }

    @ShellMethod(value = "Show book by id (example: sbcid 1)", key = {"sbcid", "show book comments"})
    @Transactional(readOnly = true)
    public void showCommentsByBookId(@ShellOption long bookId) {
        commentRepository.findAllByBook_Id(bookId).forEach(comment -> ioService.printString(outputFormatter.formatComment(comment)));
    }

    //For example:
    //sc comment_text_new 1
    @ShellMethod(value = "Save comment (example: sc comment_text_new 1)",
            key = {"sc", "save comment"})
    @Transactional
    public void saveComment(@ShellOption String title,
                            @ShellOption long bookId) {
        CommentEntity comment = objectFactory.createCommentEntity(title, bookId);
        commentRepository.save(comment);
    }

}
