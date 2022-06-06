package ru.homework.librarymongo.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Genre")
public class Genre {
    @Id
    private String id;
    @Field(name = "genretitle")
    @Indexed(unique = true)
    private String genreTitle;
    @DBRef
    private List<Book> bookList;

    public Genre(String genreTitle) {
        this.genreTitle = genreTitle;
        this.bookList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Genre(id=" + this.getId() + ", genreTitle=" + this.getGenreTitle() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Genre)) {
            return false;
        } else {
            Genre other = (Genre) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$genreTitle = this.getGenreTitle();
                Object other$genreTitle = other.getGenreTitle();
                if (this$genreTitle == null) {
                    if (other$genreTitle != null) {
                        return false;
                    }
                } else if (!this$genreTitle.equals(other$genreTitle)) {
                    return false;
                }
                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Genre;
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $genreTitle = this.getGenreTitle();
        result = result * 59 + ($genreTitle == null ? 43 : $genreTitle.hashCode());
        Object $bookList = this.getBookList();
        result = result * 59 + ($bookList == null ? 43 : $bookList.hashCode());
        return result;
    }
}
