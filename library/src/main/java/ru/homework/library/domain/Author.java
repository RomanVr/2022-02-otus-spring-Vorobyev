package ru.homework.library.domain;

import lombok.*;

import java.sql.Date;

@Getter
public class Author {
    private long id;
    private final String name;
    private final String lastName;
    private final Date dateOfBirth;
    private final String gender;

    public Author(String name, String lastName, Date dateOfBirth, String gender) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Author(long id, String name, String lastName, Date dateOfBirth, String gender) {
        this(name, lastName, dateOfBirth, gender);
        this.id = id;
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
        return result;
    }
}