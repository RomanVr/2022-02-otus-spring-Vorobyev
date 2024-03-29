package ru.homework.librarymongo.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.homework.librarymongo.domain.Author;
import ru.homework.librarymongo.service.AuthorService;

import java.sql.Date;
import java.sql.SQLException;
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
        Author newAuthor = new Author(name, lastName, dateOfBirth, gender);
        return String.format("Author insert to db with id: %s%n", authorService.insert(newAuthor));
    }

    @ShellMethod(value = "update Author", key = {"upAuthor"})
    public String updateAuthor(
            @ShellOption String id,
            @ShellOption String name,
            @ShellOption String lastName,
            @ShellOption Date dateOfBirth,
            @ShellOption(defaultValue = "man") String gender) {
        Author newAuthor = new Author(id, name, lastName, dateOfBirth, gender, null);
        return String.format("Author update to db with id: %s%n", authorService.update(newAuthor));
    }

    @ShellMethod(value = "get author by id", key = {"getAuthor"})
    public void getAuthorById(@ShellOption String id) {
        authorService.getById(id).ifPresent(author -> System.out.printf("%s%n", author));
    }

    @ShellMethod(value = "get author by name lastName", key = {"getAuthorName"})
    public String getAuthorByNameFamily(@ShellOption String name, @ShellOption String lastName) {
        return String.format("%s%n", authorService.getByNameFamily(name, lastName).toString());
    }

    @ShellMethod(value = "delete author by id", key = {"delAuthor"})
    public String deleteAuthorById(@ShellOption String id) {
        try {
            authorService.deleteById(id);
        } catch (SQLException ex) {
            return ex.getMessage();
        }
        return String.format("Author with id: %s was deleted %n", id);
    }
}
