package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "first_name", nullable = false, length = 50)
    private String  firstName;
    @Column(name = "family_name", nullable = false, length = 50)
    private String familyName;
    @Column(name = "second_name", length = 50)
    private String secondName;

    public Author(String firstName, String familyName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.familyName = familyName;
    }
}
