package ua.com.owu.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "authors")
@Entity
@EqualsAndHashCode(exclude = {"id","authors"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bookName;
    private String describtion;
    private String date;


    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "author_book",
            inverseJoinColumns = @JoinColumn(name = "books_id"),
            joinColumns = @JoinColumn(name = "authors_id"))
    private List<Author> authors = new ArrayList<>();


    public Book(String bookName, String describtion, String date) {
        this.bookName = bookName;
        this.describtion = describtion;
        this.date = date;
    }

}
