package ru.homework.library.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "book_commentary")
public class BookCommentary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "commentary", nullable = false)
    private String commentary;
    @ManyToOne()
    @JoinColumn(name = "book_id")
    private Book book;

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
                return this.getId() == other.getId();
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BookCommentary;
    }

    @Override
    public int hashCode() {
        int result = 1;
        long $id = this.getId();
        result = result * 59 + (int) ($id >>> 32 ^ $id);
        Object $commentary = this.getCommentary();
        result = result * 59 + ($commentary == null ? 43 : $commentary.hashCode());
        Object $book = this.getBook();
        result = result * 59 + ($book == null ? 43 : $book.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "BookCommentary(id=" + this.getId() +
                ", commentary=" + this.getCommentary() + ")";
    }
}
