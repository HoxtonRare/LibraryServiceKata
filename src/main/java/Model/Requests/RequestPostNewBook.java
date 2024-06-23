package Model.Requests;

import Entity.Author;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RequestPostNewBook {
    private String bookTitle;
    private Author author;
}
