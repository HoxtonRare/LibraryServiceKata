package Model.Responses;

import Entity.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseGetAuthorsBooks {
    private int errorCode;
    private String errorMessage;
    private String errorDetails;

    @Data
    public class Book {
        private long id;
        private String bookTitle;
        private Author author;
    }
}
