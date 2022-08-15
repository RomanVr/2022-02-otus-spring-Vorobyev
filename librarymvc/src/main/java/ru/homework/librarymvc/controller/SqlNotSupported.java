package ru.homework.librarymvc.controller;

public class SqlNotSupported extends Exception {

    public SqlNotSupported(String operation, long id) {
        super(String.format("Operation '%s' cannot be performed with id: %d", operation, id));
    }
}
