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

    @GetMapping("/new")
    public String authorNew(Model model) {
        model.addAttribute("author", new AuthorDto());
        return "authorNew";
    }

    @Validated
    @PostMapping("/")
    public String authorinsert(@Valid @ModelAttribute("author") AuthorDto author,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "authorNew";
        }
        authorService.insert(author.toDomainObject());
        return "redirect:/authors/";
    }

    @GetMapping("/edit")
    public String editAuthor(@RequestParam("id") long id, Model model) {
        Author author = authorService.getById(id).orElseThrow(
                () -> new NotFoundException("Authors", id));
        model.addAttribute("author", AuthorDto.fromDomainObject(author));
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
        return "redirect:/authors/";
    }

    @DeleteMapping("/delete")
    public String deleteAuthor(@RequestParam("id") long id) throws SqlNotSupported {
        try {
            authorService.deleteById(id);
        } catch (Exception ex) {
            throw new SqlNotSupported("delete", id);
        }
        return "redirect:/authors/";
    }
}
