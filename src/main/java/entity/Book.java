package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

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
    @Column(name = "author_id", nullable = false)
    private long authorId;
    @Column(name = "updated")
    private Timestamp updated;

    public Book(long id, String bookTitle, long authorId) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.authorId = authorId;
    }
}
