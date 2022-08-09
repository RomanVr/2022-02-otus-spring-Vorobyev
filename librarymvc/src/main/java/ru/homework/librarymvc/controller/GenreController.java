package ru.homework.librarymvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.homework.librarymvc.domain.Genre;
import ru.homework.librarymvc.service.GenreService;

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
}
