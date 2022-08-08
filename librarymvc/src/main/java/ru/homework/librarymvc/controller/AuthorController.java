package ru.homework.librarymvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.homework.librarymvc.domain.Author;
import ru.homework.librarymvc.dto.AuthorDto;
import ru.homework.librarymvc.service.AuthorService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/")
    public String authorList(Model model) {
        List<Author> authors = authorService.getAll();
        model.addAttribute("authors", authors);
        return "authorList";
    }

    @GetMapping("/edit")
    public String editAuthor(@RequestParam("id") long id, Model model) {
        Author author = authorService.getById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("author", author);
        return "authorEdit";
    }

    @Validated
    @PostMapping("/edit")
    public String saveAuthor(@Valid @ModelAttribute("author") AuthorDto author,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "authorEdit";
        }
        authorService.update(author.toDomainObject());
        return "redirect:/authors";
    }
}
