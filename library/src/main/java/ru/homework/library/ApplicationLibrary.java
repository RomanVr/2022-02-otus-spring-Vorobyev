package ru.homework.library;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ApplicationLibrary {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(ApplicationLibrary.class, args);

        Console.main(args);
    }
}
