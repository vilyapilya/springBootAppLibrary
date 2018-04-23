package pack.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class User {

    private int id;
    String firstName;
    String lastName;

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

    @Column(name="first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
