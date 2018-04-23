package pack.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

    private int id;
    String firstName;
    String lastName
}
