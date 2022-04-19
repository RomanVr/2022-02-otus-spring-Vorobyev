package ru.homework.library.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Genre {
    private long id;
    private final String genreTitle;
    private final List<Book> bookList;

    public Genre(String genreTitle) {
        this.genreTitle = genreTitle;
        this.bookList = new ArrayList<>();
    }

    public Genre(long id, String genreTitle) {
        this(genreTitle);
        this.id = id;
    }

    @Override
    public String toString() {
        long id1 = this.getId();
        StringBuilder genreBuilder = new StringBuilder("Genre(id=" + id1 + ", genreTitle=" + this.getGenreTitle() + ")");
        genreBuilder.append("\n");
        for (Book book : this.bookList) {
            genreBuilder.append("\t");
            genreBuilder.append(book.toString());
            genreBuilder.append("\n");
        }
        return genreBuilder.toString();
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
        long $id = this.getId();
        result = result * 59 + (int) ($id >>> 32 ^ $id);
        Object $genreTitle = this.getGenreTitle();
        result = result * 59 + ($genreTitle == null ? 43 : $genreTitle.hashCode());
        Object $bookList = this.getBookList();
        result = result * 59 + ($bookList == null ? 43 : $bookList.hashCode());
        return result;
    }

}
