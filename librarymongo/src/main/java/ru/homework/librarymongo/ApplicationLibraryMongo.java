package ru.homework.librarymongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ApplicationLibraryMongo {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(ApplicationLibraryMongo.class, args);

//        Console.main(args);
    }
}
