package Model.Requests;

import Entity.Author;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestGetAuthorBooksXML {
    @XmlTransient
    private Author author;

    @XmlElement(name = "author_id")
    public long getAuthorId() {
        return author.getId();
    }
}
