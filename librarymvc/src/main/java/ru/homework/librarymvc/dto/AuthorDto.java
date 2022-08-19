package ru.homework.librarymvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.homework.librarymvc.domain.Author;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    private long id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 3, max = 10, message = "{name-field-should-has-expected-size}")
    private String name;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    private String lastName;

    private Date dateOfBirth;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    private String gender;

    private String fullName;

    public Author toDomainObject() {
        return new Author(id, name, lastName, dateOfBirth, gender, null);
    }

    public static AuthorDto fromDomainObject(Author author) {
        return new AuthorDto(author.getId(),
                author.getName(),
                author.getLastName(),
                author.getDateOfBirth(),
                author.getGender(),
                author.getName() + " " + author.getLastName());
    }
}
