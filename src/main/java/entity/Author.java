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
    @XmlElement(name = "id")
    private long id;
    @XmlElement(name = "first_name")
    private String  firstName;
    @XmlElement(name = "family_name")
    private String familyName;
    @XmlElement(name = "second_name")
    private String secondName;
    @XmlElement(name = "birth_date")
    private String birthDate;
}
