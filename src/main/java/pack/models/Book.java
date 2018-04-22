package pack.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="books")
public class Book {

    String title;
    int author_id;
    private int id;

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

    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="author_id")
    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public Book(String title, int author_id) {
        this.title = title;
        this.author_id = author_id;
    }

    public Book() {}
}
