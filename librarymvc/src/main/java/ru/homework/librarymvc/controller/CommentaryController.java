package ru.homework.librarymvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.homework.librarymvc.domain.Book;
import ru.homework.librarymvc.domain.BookCommentary;
import ru.homework.librarymvc.dto.BookDto;
import ru.homework.librarymvc.dto.CommentaryDto;
import ru.homework.librarymvc.service.BookCommentService;
import ru.homework.librarymvc.service.BookService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/commentaries")
public class CommentaryController {
    private final BookCommentService commentService;
    private final BookService bookService;

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

}
