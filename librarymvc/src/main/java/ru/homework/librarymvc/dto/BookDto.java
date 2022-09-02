package ru.homework.librarymvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.homework.librarymvc.domain.Book;

import javax.validation.Valid;
import javax.validation.constraints.*;

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

    @NotNull
    @Min(value = 1, message = "{id-field-should-not-be-blank}")
    private long genre_id;

    @NotNull
    @Min(value = 1, message = "{id-field-should-not-be-blank}")
    private long author_id;

    private AuthorDto author;
    private GenreDto genre;

    public Book toDomainObject() {
        return new Book(
                id,
                bookTitle,
                preview,
                null,
                null,
                null);
    }

    public static BookDto fromDomainObject(Book book) {
        return new BookDto(
                book.getId(),
                book.getBookTitle(),
                book.getPreview(),
                book.getGenre().getId(),
                book.getAuthor().getId(),
                AuthorDto.fromDomainObject(book.getAuthor()),
                GenreDto.fromDomainObject(book.getGenre()));
    }
}
