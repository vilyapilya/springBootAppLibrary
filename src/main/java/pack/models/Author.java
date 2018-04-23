package pack.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="authors")
public class Author {

    private int id;
    String lastName;
    String firstName;

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="id", unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Author (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(){}

}
