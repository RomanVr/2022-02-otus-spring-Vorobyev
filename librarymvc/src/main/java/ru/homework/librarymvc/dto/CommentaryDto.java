package ru.homework.librarymvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.homework.librarymvc.domain.BookCommentary;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentaryDto {
    private long id;

    @NotBlank
    private String commentary;
    @NotNull
    private long book_id;

    private BookDto book;

    public BookCommentary toDomainObject() {
        return new BookCommentary(id, commentary, null);
    }

    public static CommentaryDto fromDomainObject(BookCommentary bookCommentary) {
        return new CommentaryDto(
                bookCommentary.getId(),
                bookCommentary.getCommentary(),
                bookCommentary.getBook().getId(),
                BookDto.fromDomainObject(bookCommentary.getBook())
        );
    }
}
