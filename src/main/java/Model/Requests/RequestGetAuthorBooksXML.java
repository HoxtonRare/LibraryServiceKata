package Model.Requests;

import Entity.Author;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestGetAuthorBooksXML {
    private Author author;
}
