package ru.homework.librarymvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.homework.librarymvc.domain.Genre;
import ru.homework.librarymvc.dto.GenreDto;
import ru.homework.librarymvc.service.GenreService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/")
    public String genreList(Model model) {
        List<Genre> genres = genreService.getAll();
        model.addAttribute("genres", genres);
        return "genreList";
    }

    @GetMapping("/new")
    public String genreNew(Model model) {
        model.addAttribute("genre", new GenreDto());
        return "genreNew";
    }

    @Validated
    @PostMapping("/")
    public String genreInsert(@Valid @ModelAttribute("genre") GenreDto genre,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "genreNew";
        }
        genreService.insert(genre.toDomainObject());
        return "redirect:/genres/";
    }

    @GetMapping("/edit")
    public String genreEdit(@RequestParam("id") long id, Model model) {
        Genre genre = genreService.getById(id).orElseThrow(
                () -> new NotFoundException("Genres", id)
        );
        model.addAttribute("genre", GenreDto.fromDomainObject(genre));
        return "genreEdit";
    }

    @Validated
    @PostMapping("/edit")
    public String saveGenre(@Valid @ModelAttribute("genre") GenreDto genre,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "genreEdit";
        }
        genreService.update(genre.toDomainObject());
        return "redirect:/genres/";
    }

    @DeleteMapping("/delete")
    public String deleteGenre(@RequestParam("id") long id) throws SqlNotSupported {
        try {
            genreService.deleteById(id);
        } catch (Exception ex) {
            throw new SqlNotSupported("delete", id);
        }
        return "redirect:/genres/";
    }
}
