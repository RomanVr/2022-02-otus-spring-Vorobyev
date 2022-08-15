package ru.homework.librarymvc.controller;

public class NotFoundException extends RuntimeException {
    NotFoundException(String entity, long id) {
        super(String.format("Entity '%s' with id: %d not found", entity, id));
    }
}
