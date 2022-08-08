package ru.homework.librarymvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.homework.librarymvc.domain.Author;
import ru.homework.librarymvc.domain.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AuthorDto {

    private long id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 3, max = 10, message = "{name-field-should-has-expected-size}")
    private String name;

    private String lastName;

    private Date dateOfBirth;

    private String gender;

    private List<Book> bookList;

    public AuthorDto(String name) {
        this.name = name;
    }

    public AuthorDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author toDomainObject() {
        List<Book> newBookList = null;
        if (bookList != null ) {
            newBookList = List.copyOf(this.bookList);
        }
        return new Author(id, name, lastName, dateOfBirth, gender, newBookList);
    }

    public static AuthorDto fromDomainObject(Author author) {
        List<Book> newBookList = null;
        if (author.getBookList() != null ) {
            newBookList = List.copyOf(author.getBookList());
        }
        return new AuthorDto(author.getId(), author.getName(), author.getLastName(), author.getDateOfBirth(),author.getGender(),newBookList);
    }}
