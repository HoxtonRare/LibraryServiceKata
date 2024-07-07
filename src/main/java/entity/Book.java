package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "book_title", nullable = false, length = 100)
    private String bookTitle;
    @ManyToOne
    @Column(name = "author_id", nullable = false)
    private Author author;
    private LocalDate updated;

    public Book(long id, String bookTitle, Author author) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.author = author;
    }
}
