package ua.com.owu.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(exclude = "books")
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"id","books"})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fistName;
    private String secondName;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "author_book",
        inverseJoinColumns = @JoinColumn(name = "books_id"),
        joinColumns = @JoinColumn(name = "authors_id"))
    private List<Book> books = new ArrayList<>();


    public Author(String fistName, String secondName) {
        this.fistName = fistName;
        this.secondName = secondName;
    }
}
