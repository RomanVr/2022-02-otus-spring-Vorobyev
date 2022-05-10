package ru.homework.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
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

    @ShellMethod(value = "create new Book", key = {"newBook"})
    public String createNewBook(
            @ShellOption String bookTitle,
            @ShellOption String preview,
            @ShellOption long author_id,
            @ShellOption long genre_id
    ) {
        Book newBook = new Book(0, bookTitle, preview, null, null, null);
        return String.format("Book insert to db with id: %d%n", bookService.insert(newBook, author_id, genre_id));
    }

    @ShellMethod(value = "update Book", key = {"upBook"})
    public String updateBook(
            @ShellOption long id,
            @ShellOption String bookTitle,
            @ShellOption String preview
    ) {
        Book newBook = new Book(id, bookTitle, preview, null, null, null);
        return String.format("Book update to db with id: %d%n", bookService.update(newBook));
    }

    @ShellMethod(value = "get Book by id", key = {"getBook"})
    public void getBookById(@ShellOption long id) {
        bookService.getById(id).ifPresent(book -> System.out.println(String.format("%s%n", book)));
    }

    @ShellMethod(value = "get Book by title", key = {"getBookTitle"})
    public String getBookByTitle(@ShellOption String bookTitle) {
        return String.format("%s%n", bookService.getByTitle(bookTitle));
    }

    @ShellMethod(value = "delete Book by id", key = {"delBook"})
    public String deleteBookById(@ShellOption long id) {
        bookService.deleteById(id);
        return String.format("Book with :id was deleted %d%n", id);
    }

    @ShellMethod(value = "find book by id Author", key = {"findBookAuthor"})
    public String findBooksByAuthorId(@ShellOption long author_id) {
        List<Book> bookList = bookService.findBooksByAuthorId(author_id);
        if (bookList.size() != 0) {
            return String.format(
                    "All books the Author id: %d%n%s%n",
                    author_id,
                    bookList.stream().map(Objects::toString)
                            .collect(Collectors.joining("\n"))
            );
        }
        return String.format("There are no Books from the Author with id: %d%n", author_id);
    }

    @ShellMethod(value = "find book by id Genre", key = {"findBookGenre"})
    public String findBooksByGenreId(@ShellOption long id) {
        List<Book> bookList = bookService.findBooksByGenreId(id);
        if (bookList.size() != 0) {
            return String.format(
                    "All books the Genre id: %d%n%s%n",
                    id,
                    bookList.stream().map(Objects::toString)
                            .collect(Collectors.joining("\n"))
            );
        }
        return String.format("There are no Books from the Genre with id: %d%n", id);
    }
}
