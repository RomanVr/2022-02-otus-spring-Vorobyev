package ru.homework.library.domain;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Author {
    private long id;
    private final String name;
    private final String lastName;
    private final Date dateOfBirth;
    private final String gender;
    private final List<Book> bookList;

    public Author(String name, String lastName, Date dateOfBirth, String gender) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bookList = new ArrayList<>();
    }

    public Author(long id, String name, String lastName, Date dateOfBirth, String gender) {
        this(name, lastName, dateOfBirth, gender);
        this.id = id;
    }

    @Override
    public String toString() {
        long id1 = this.getId();
        StringBuilder authorStringBuilder = new StringBuilder("Author(id=" + id1 + ", name=" + this.getName() + ", lastName=" + this.getLastName() + ", dateOfBirth=" + this.getDateOfBirth() + ", gender=" + this.getGender() + ")");
        authorStringBuilder.append("\n");
        for (Book book : this.bookList) {
            authorStringBuilder.append("\t");
            authorStringBuilder.append(book.toString());
            authorStringBuilder.append("\n");
        }
        return authorStringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Author)) {
            return false;
        } else {
            Author other = (Author) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
/*                 TODO Необходимо пересмотреть стратегию сравнения свойств Автора
                 При сравнении у Авторов могут совпадать Имена, Фамилии, Пол, Дата рождения
                 Уникальными должны быть Имя и Фамилия
                 При сравнении двух авторов надо ли сравнивать их все свойства? Или
                 только можно обойтись Именем и Фамилией?*/

/*                label73:
                {
                    Object this$name = this.getName();
                    Object other$name = other.getName();
                    if (this$name == null) {
                        if (other$name == null) {
                            break label73;
                        }
                    } else if (this$name.equals(other$name)) {
                        break label73;
                    }
                    return false;
                }

                Object this$family = this.getFamily();
                Object other$family = other.getFamily();
                if (this$family == null) {
                    if (other$family != null) {
                        return false;
                    }
                } else if (!this$family.equals(other$family)) {
                    return false;
                }

                label59:
                {
                    Object this$dateOfBirth = this.getDateOfBirth();
                    Object other$dateOfBirth = other.getDateOfBirth();
                    if (this$dateOfBirth == null) {
                        if (other$dateOfBirth == null) {
                            break label59;
                        }
                    } else if (this$dateOfBirth.equals(other$dateOfBirth)) {
                        break label59;
                    }

                    return false;
                }

                Object this$gender = this.getGender();
                Object other$gender = other.getGender();
                if (this$gender == null) {
                    if (other$gender != null) {
                        return false;
                    }
                } else if (!this$gender.equals(other$gender)) {
                    return false;
                }*/

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Author;
    }

    @Override
    public int hashCode() {
        int result = 1;
        long $id = this.getId();
        result = result * 59 + (int) ($id >>> 32 ^ $id);
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $family = this.getLastName();
        result = result * 59 + ($family == null ? 43 : $family.hashCode());
        Object $dateOfBirth = this.getDateOfBirth();
        result = result * 59 + ($dateOfBirth == null ? 43 : $dateOfBirth.hashCode());
        Object $gender = this.getGender();
        result = result * 59 + ($gender == null ? 43 : $gender.hashCode());
        Object $bookList = this.getBookList();
        result = result * 59 + ($bookList == null ? 43 : $bookList.hashCode());
        return result;
    }
}
