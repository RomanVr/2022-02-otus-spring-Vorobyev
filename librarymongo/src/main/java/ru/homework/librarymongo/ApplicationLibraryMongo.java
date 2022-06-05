package ru.homework.librarymongo;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@EnableMongock
@SpringBootApplication
public class ApplicationLibraryMongo {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(ApplicationLibraryMongo.class, args);
    }
}
