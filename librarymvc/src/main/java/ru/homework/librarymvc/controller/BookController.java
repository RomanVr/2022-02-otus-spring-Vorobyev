package ru.homework.librarymvc.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.h2.engine.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.homework.librarymvc.domain.Book;
import ru.homework.librarymvc.dto.BookDto;
import ru.homework.librarymvc.service.BookService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public String bookList(Model model) {
        List<BookDto> books = bookService
                .getAll()
                .stream()
                .map(BookDto::fromDomainObject)
                .collect(Collectors.toList());
        model.addAttribute("books", books);
        return "bookList";
    }

    @GetMapping("/edit")
    public String bookEdit(@RequestParam("id") long id, Model model) {
        BookDto bookDto = BookDto.fromDomainObject(bookService.getById(id).orElseThrow(
                () -> new NotFoundException("Books", id)
        ));
        model.addAttribute("book", bookDto);
        return "bookEdit";
    }

    @Validated
    @PostMapping("/edit")
    public String saveBook(@Valid @ModelAttribute("book") BookDto book,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "bookEdit";
        }
        bookService.update(book.toDomainObject());
        return "redirect:/books";
    }
}
