package ru.homework.librarymvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.homework.librarymvc.domain.Book;
import ru.homework.librarymvc.dto.AuthorDto;
import ru.homework.librarymvc.dto.BookDto;
import ru.homework.librarymvc.dto.CommentaryDto;
import ru.homework.librarymvc.dto.GenreDto;
import ru.homework.librarymvc.service.AuthorService;
import ru.homework.librarymvc.service.BookCommentService;
import ru.homework.librarymvc.service.BookService;
import ru.homework.librarymvc.service.GenreService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    private final BookCommentService commentService;

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

    @GetMapping("/view")
    public String bookView(@RequestParam("id") long id, Model model) {
        Book book = bookService.getById(id).orElseThrow(
                () -> new NotFoundException("Books", id)
        );
        List<CommentaryDto> commentaries = commentService.findCommentsByBookId(id).stream()
                .map(CommentaryDto::fromDomainObject)
                .collect(Collectors.toList());
        model.addAttribute("book", BookDto.fromDomainObject(book));
        model.addAttribute("commentaries", commentaries);
        return "bookView";
    }

    @GetMapping("/edit")
    public String bookEdit(@RequestParam("id") long id, Model model) {
        Book book = bookService.getById(id).orElseThrow(
                () -> new NotFoundException("Books", id)
        );
        BookDto bookDto = BookDto.fromDomainObject(book);
        model.addAttribute("book", bookDto);
        model.addAttribute("authors", authorService.getAll()
                .stream().map(AuthorDto::fromDomainObject)
                .collect(Collectors.toList()));
        model.addAttribute("genres", genreService.getAll()
                .stream().map(GenreDto::fromDomainObject)
                .collect(Collectors.toList()));
        return "bookEdit";
    }

    @Validated
    @PostMapping("/edit")
    public String saveBook(@Valid @ModelAttribute("book") BookDto book,
                           BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.getAll()
                    .stream().map(AuthorDto::fromDomainObject)
                    .collect(Collectors.toList()));
            model.addAttribute("genres", genreService.getAll()
                    .stream().map(GenreDto::fromDomainObject)
                    .collect(Collectors.toList()));
            return "bookEdit";
        }
        bookService.update(book.toDomainObject(), book.getAuthor_id(), book.getGenre_id());
        return "redirect:/books/view?id=" + book.getId();
    }
}
