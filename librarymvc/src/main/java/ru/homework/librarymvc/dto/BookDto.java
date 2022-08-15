package ru.homework.librarymvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.homework.librarymvc.domain.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private long id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 3, max = 10, message = "{name-field-should-has-expected-size}")
    private String bookTitle;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    private String preview;

    public Book toDomainObject() {
        return new Book(id, bookTitle, preview, null, null, null);
    }

    public static BookDto fromDomainObject(Book book) {
        return new BookDto(book.getId(), book.getBookTitle(), book.getPreview());
    }
}
