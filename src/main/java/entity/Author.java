package entity;

import javax.xml.bind.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.NONE)
@Table(name = "Author")
public class Author {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @XmlElement(name = "id")
    @Column(name = "id")
    private long id;
    @Column(name = "first_name")
    @XmlElement(name = "first_name")
    private String  firstName;
    @Column(name = "family_name")
    @XmlElement(name = "family_name")
    private String familyName;
    @Column(name = "second_name")
    @XmlElement(name = "second_name")
    private String secondName;
    @Column(name = "birth_date")
    @XmlElement(name = "birth_date")
    private String birthDate;
}
