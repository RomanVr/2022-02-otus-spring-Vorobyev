package ru.homework.librarymvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.homework.librarymvc.domain.Book;
import ru.homework.librarymvc.service.BookService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public String bookList(Model model) {
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "bookList";
    }
}
