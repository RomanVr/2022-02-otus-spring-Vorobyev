package ru.homework.librarymongo.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.homework.librarymongo.domain.Genre;
import ru.homework.librarymongo.service.GenreService;


import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class GenreCommands {

    private final GenreService genreService;

    @ShellMethod(value = "get all Genres", key = {"genres"})
    public String getAllGenres() {
        List<Genre> genreList = genreService.getAll();
        if (genreList.size() != 0) {
            return String.format("All genres in db:%n%s%n", genreList.stream().map(Objects::toString)
                    .collect(Collectors.joining("\n")));
        }
        return "No genres in the db";
    }

    @ShellMethod(value = "create new Genre", key = {"newGenre"})
    public String createNewGenre(@ShellOption String genreTitle) {
        Genre newGenre = new Genre(genreTitle);
        return String.format("Genre inset to db with id: %s%n", genreService.insert(newGenre));
    }

    @ShellMethod(value = "update Genre", key = {"upGenre"})
    public String updateGenre(@ShellOption String id, @ShellOption String genreTitle) {
        Genre newGenre = new Genre(id, genreTitle, null);
        return String.format("Genre update to db with id: %s%n", genreService.update(newGenre));
    }

    @ShellMethod(value = "get genre by id", key = {"getGenre"})
    public void getGenreById(@ShellOption String id) {
        genreService.getById(id).ifPresent(genre -> System.out.printf("%s%n", genre));
    }

    @ShellMethod(value = "get genre by title", key = {"getGenreTitle"})
    public String getGenreByTitle(@ShellOption String title) {
        return String.format("%s%n", genreService.getByTitle(title));
    }

    @ShellMethod(value = "delete genre by id", key = {"delGenre"})
    public String deleteGenreById(@ShellOption String id) {
        try {
            genreService.deleteById(id);
        } catch (SQLException ex) {
            return ex.getMessage();
        }
        return String.format("Genre with :id was deleted %s%n", id);
    }
}
