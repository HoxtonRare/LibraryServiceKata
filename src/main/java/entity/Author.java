package entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.NONE)
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
    @Column(name = "birth_date", nullable = false)
    private String birthDate;
}
