package ru.homework.librarymvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.homework.librarymvc.domain.Genre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {
    private long id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 3, max = 10, message = "{name-field-should-has-expected-size}")
    private String genreTitle;

    public Genre toDomainObject() {
        return new Genre(id, genreTitle, null);
    }

    public static GenreDto fromDomainObject(Genre genre) {
        return new GenreDto(genre.getId(), genre.getGenreTitle());
    }
}
