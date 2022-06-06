package ru.homework.librarymongo.domain;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Author")
@CompoundIndexes({@CompoundIndex(name = "name_idx", def = "{'name': 1, 'lastname': 1}")})
public class Author {
    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @Field(name = "lastname")
    private String lastName;
    @Field(name = "datebirth")
    private Date dateOfBirth;
    @Field(name = "gender")
    private String gender;
    @DBRef
    private List<Book> bookList;

    public Author(String name, String lastName, Date dateOfBirth, String gender) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bookList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Author(id=" + this.getId() + ", name=" + this.getName() +
                ", lastName=" + this.getLastName() + ", dateOfBirth=" + this.getDateOfBirth() +
                ", gender=" + this.getGender() + ")";
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
                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$lastName = this.getLastName();
                Object other$lastName = other.getLastName();
                if (this$lastName == null) {
                    if (other$lastName != null) {
                        return false;
                    }
                } else if (!this$lastName.equals(other$lastName)) {
                    return false;
                }

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
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $lastName = this.getLastName();
        result = result * 59 + ($lastName == null ? 43 : $lastName.hashCode());
        Object $dateOfBirth = this.getDateOfBirth();
        result = result * 59 + ($dateOfBirth == null ? 43 : $dateOfBirth.hashCode());
        Object $gender = this.getGender();
        result = result * 59 + ($gender == null ? 43 : $gender.hashCode());
        Object $bookList = this.getBookList();
        result = result * 59 + ($bookList == null ? 43 : $bookList.hashCode());
        return result;
    }
}
