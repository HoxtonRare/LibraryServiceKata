package model.responses;

import entity.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseGetAuthorsBooks {
    private int errorCode;
    private String errorMessage;
    private String errorDetails;
    private List<Book> books;

    @Data
    public class Book {
        private long id;
        private String bookTitle;
        private Author author;
    }
}
