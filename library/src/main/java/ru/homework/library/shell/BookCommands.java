package ru.homework.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.homework.library.domain.Book;
import ru.homework.library.service.BookService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class BookCommands {
    private final BookService bookService;

    @ShellMethod(value = "get all books", key = {"books"})
    public String getAllBooks() {
        List<Book> bookList = bookService.getAll();
        if (bookList.size() != 0) {
            return String.format(
                    "All books in db: %n%s%n",
                    bookList.stream().map(Objects::toString).collect(Collectors.joining("\n")));
        }
        return "No books in the db";
    }
}
