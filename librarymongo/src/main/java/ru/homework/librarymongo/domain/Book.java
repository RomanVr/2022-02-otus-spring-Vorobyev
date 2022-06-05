package ru.homework.librarymongo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Book")
public class Book {
    @Id
    private long id;
    @Field(name = "booktitle")
    @Indexed(unique = true)
    private String bookTitle;
    @Field(name = "preview")
    private String preview;
    @DBRef
    private Author author;
    @DBRef
    private Genre genre;
    @DBRef
    private List<BookCommentary> bookCommentaries;

    @Override
    public String toString() {
        long id1 = this.getId();
        StringBuilder bookStringBuilder = new StringBuilder(
                "Book(id=" + id1 + ", bookTitle=" + this.getBookTitle() + ", preview=" + this.getPreview() + ")");
        if (this.author != null) {
            bookStringBuilder.append("\n\t");
            bookStringBuilder.append(this.author);
        }
        if (this.genre != null) {
            bookStringBuilder.append("\n\t");
            bookStringBuilder.append(this.genre);
        }
        return bookStringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Book)) {
            return false;
        } else {
            Book other = (Book) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$bookTitle = this.getBookTitle();
                Object other$bookTitle = other.getBookTitle();
                if (this$bookTitle == null) {
                    if (other$bookTitle != null) {
                        return false;
                    }
                } else if (!this$bookTitle.equals(other$bookTitle)) {
                    return false;
                }
                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Book;
    }

    @Override
    public int hashCode() {
        int result = 1;
        long $id = this.getId();
        result = result * 59 + (int) ($id >>> 32 ^ $id);
        Object $bookTitle = this.getBookTitle();
        result = result * 59 + ($bookTitle == null ? 43 : $bookTitle.hashCode());
        Object $preview = this.getPreview();
        result = result * 59 + ($preview == null ? 43 : $preview.hashCode());
        return result;
    }
}
