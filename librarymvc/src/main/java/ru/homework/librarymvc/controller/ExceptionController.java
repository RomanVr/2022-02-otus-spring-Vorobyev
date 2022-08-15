package ru.homework.librarymvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({NotFoundException.class, SqlNotSupported.class})
    public String handleNotFound(Exception ex, Model model) {
        model.addAttribute("errorMsg", ex.getMessage());
        return "error";
    }
}
