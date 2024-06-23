package Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book")
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
}
