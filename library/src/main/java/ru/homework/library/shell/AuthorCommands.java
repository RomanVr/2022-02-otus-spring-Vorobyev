package ru.homework.library.shell;

import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Optional;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.homework.library.domain.Author;
import ru.homework.library.service.AuthorService;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class AuthorCommands {

    private final AuthorService authorService;

    @ShellMethod(value = "get all Authors", key = {"authors"})
    public String getAllAuthors() {
        List<Author> authorList = authorService.getAll();
        if (authorList.size() != 0) {
            return String.format(
                    "All authors in db: %n%s%n",
                    authorList.stream().map(Objects::toString)
                            .collect(Collectors.joining("\n")));
        }
        return "No authors in the db";
    }

    @ShellMethod(value = "create new Author", key = {"newAuthor", "createAuthor"})
    public String createNewAuthor(
            @ShellOption String name,
            @ShellOption String lastName,
            @ShellOption Date dateOfBirth,
            @ShellOption(defaultValue = "man") String gender) {
        Author newAuthor = new Author(0, name, lastName, dateOfBirth, gender);
        return String.format("Author insert to db with id: %d%n", authorService.insert(newAuthor));
    }

    @ShellMethod(value = "update Author", key = {"upAuthor"})
    public String updateAuthor(
            @ShellOption long id,
            @ShellOption String name,
            @ShellOption String lastName,
            @ShellOption Date dateOfBirth,
            @ShellOption(defaultValue = "man") String gender) {
        Author newAuthor = new Author(id, name, lastName, dateOfBirth, gender);
        return String.format("Author update to db with id: %d%n", authorService.update(newAuthor));
    }

    @ShellMethod(value = "get author by id", key = {"getAuthor"})
    public void getAuthorById(@ShellOption long id) {
        authorService.getById(id).ifPresent(author -> System.out.println(String.format("%s%n", author)));
    }

    @ShellMethod(value = "get author by name lastName", key = {"getAuthorName"})
    public String getAuthorByNameFamily(@ShellOption String name, @ShellOption String lastName) {
        return String.format("%s%n", authorService.getByNameFamily(name, lastName).toString());
    }

    @ShellMethod(value = "delete author by id", key = {"delAuthor"})
    public String deleteAuthorById(@ShellOption long id) {
        authorService.deleteById(id);
        return String.format("Author with :id was deleted %d%n", id);
    }
}
