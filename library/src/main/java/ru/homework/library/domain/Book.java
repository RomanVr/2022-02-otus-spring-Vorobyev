package ru.homework.library.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "booktitle", nullable = false, unique = true)
    private String bookTitle;
    @Column(name = "preview", nullable = false)
    private String preview;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
    @OneToMany(orphanRemoval = true, mappedBy = "book")
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
