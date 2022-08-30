package ru.homework.librarymvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.homework.librarymvc.domain.Book;
import ru.homework.librarymvc.domain.BookCommentary;
import ru.homework.librarymvc.dto.BookDto;
import ru.homework.librarymvc.dto.CommentaryDto;
import ru.homework.librarymvc.service.BookCommentService;
import ru.homework.librarymvc.service.BookService;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/commentaries")
public class CommentaryController {
    private final BookCommentService commentService;
    private final BookService bookService;

    @GetMapping("/new")
    public String commentNew(
            @RequestParam("book_id") long book_id,
            Model model) {
        model.addAttribute("commentary", new CommentaryDto());
        model.addAttribute("book", bookService.getById(book_id)
                .orElseThrow(() -> new NotFoundException("book", book_id)));
        return "commentaryNew";
    }

    @Validated
    @PostMapping("/")
    public String commentaryInsert(@Valid @ModelAttribute("commentary") CommentaryDto commentary,
                                   BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            Book book = bookService.getById(commentary.getBook_id())
                    .orElseThrow(() -> new NotFoundException("book", commentary.getBook_id()));
            model.addAttribute("book", BookDto.fromDomainObject(book));
            return "commentaryNew";
        }
        commentService.insert(commentary.toDomainObject(), commentary.getBook_id());
        return "redirect:/books/view?id=" + commentary.getBook_id();
    }

    @GetMapping("/edit")
    public String commentaryEdit(@RequestParam("id") long id,
                                 @RequestParam("book_id") long book_id,
                                 Model model) {
        BookCommentary commentary = commentService.getById(id).orElseThrow(
                () -> new NotFoundException("Commentaries", id)
        );
        CommentaryDto commentaryDto = CommentaryDto.fromDomainObject(commentary);
        Book book = bookService.getById(id).orElseThrow(
                () -> new NotFoundException("Books", id)
        );
        BookDto bookDto = BookDto.fromDomainObject(book);
        model.addAttribute("commentary", commentaryDto);
        model.addAttribute("book", bookDto);
        return "commentaryEdit";
    }

    @Validated
    @PostMapping("/edit")
    public String saveComment(@Valid @ModelAttribute("commentary") CommentaryDto commentary,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book",
                    bookService.getById(commentary.getBook_id()).orElseThrow(
                            () -> new NotFoundException("Book", commentary.getBook_id())
                    ));
            return "commentaryEdit";
        }
        commentService.update(commentary.toDomainObject());
        return "redirect:/books/view?id=" + commentary.getBook_id();
    }

    @DeleteMapping("/delete")
    public String deleteCommentary(
            @RequestParam("id") long id,
            @RequestParam("book_id") long book_id) throws SqlNotSupported {
        try {
            commentService.deleteById(id);
        } catch (Exception ex) {
            throw new SqlNotSupported("delete", id);
        }
        return "redirect:/books/view?id=" + book_id;
    }
}
