package ru.homework.librarymongo.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "book_commentary")
public class BookCommentary {
    @Id
    private String id;
    @Field(name = "commentary")
    private String commentary;
    @DBRef
    private Book book;

    public BookCommentary(String commentary, Book book) {
        this.commentary = commentary;
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookCommentary(id=" + this.getId() +
                ", commentary=" + this.getCommentary() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BookCommentary)) {
            return false;
        } else {
            BookCommentary other = (BookCommentary) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                return Objects.equals(this.getId(), other.getId());
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BookCommentary;
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $commentary = this.getCommentary();
        result = result * 59 + ($commentary == null ? 43 : $commentary.hashCode());
        Object $book = this.getBook();
        result = result * 59 + ($book == null ? 43 : $book.hashCode());
        return result;
    }
}
