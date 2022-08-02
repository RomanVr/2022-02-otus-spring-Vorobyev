package ru.homework.librarymvc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "genretitle", unique = true)
    private String genreTitle;
    @OneToMany(mappedBy = "genre")
    private List<Book> bookList;

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
