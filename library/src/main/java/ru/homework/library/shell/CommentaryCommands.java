package ru.homework.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.homework.library.domain.BookCommentary;
import ru.homework.library.service.BookCommentService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class CommentaryCommands {
    private final BookCommentService commentService;

    @ShellMethod(value = "get Commentary by id", key = {"getComm"})
    public void getById(@ShellOption long id) {
        commentService.getById(id).ifPresent(commentary->System.out.println(String.format("%s%n", commentary)));
    }

    @ShellMethod(value = "create new comment for Book", key = {"newComm"})
    public String createNewCommetntary(
            @ShellOption String commentaryText,
            @ShellOption long book_id
    ) {
        BookCommentary bookCommentary = new BookCommentary(0, commentaryText, null);
        return String.format("Commentary insert to db with id: %d%n", commentService.insert(bookCommentary, book_id));
    }

    @ShellMethod(value = "find comments by id book", key = {"findCommBook"})
    public String findCommentsByBookId(@ShellOption long book_id) {
        List<BookCommentary> commentaryList = commentService.findCommentsByBookId(book_id);
        if (commentaryList.size() > 0) {
            return String.format(
                    "All comments the Book id: %d%n%s%n",
                    book_id,
                    commentaryList.stream().map(Objects::toString)
                            .collect(Collectors.joining("\n")));

        }
        return String.format("There are no Comments from the Book with id: %d%n", book_id);
    }
}
