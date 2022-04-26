package ru.homework.library.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Book {
    private long id;
    private final String bookTitle;
    private final String preview;
    private Author author;
    private Genre genre;

    public Book(String bookTitle, String preview) {
        this.bookTitle = bookTitle;
        this.preview = preview;
    }

    public Book(String bookTitle, String preview, Author author, Genre genre) {
        this(bookTitle, preview);
        this.author = author;
        this.genre = genre;
    }

    public Book(long id, String bookTitle, String preview) {
        this(bookTitle, preview);
        this.id = id;
    }

    public Book(long id, String bookTitle, String preview, Author author, Genre genre) {
        this(bookTitle, preview, author, genre);
        this.id = id;
    }

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
