package ru.homework.library.domain;

import lombok.Getter;

@Getter
public class Genre {
    private long id;
    private final String genreTitle;

    public Genre(String genreTitle) {
        this.genreTitle = genreTitle;
    }

    public Genre(long id, String genreTitle) {
        this(genreTitle);
        this.id = id;
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
        long $id = this.getId();
        result = result * 59 + (int) ($id >>> 32 ^ $id);
        Object $genreTitle = this.getGenreTitle();
        result = result * 59 + ($genreTitle == null ? 43 : $genreTitle.hashCode());
        return result;
    }

}
