package model.responses;

import entity.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.response.Response;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseGetAuthorsBooks {
    private int errorCode;
    private String errorMessage;
    private String errorDetails;
    private List<Book> books;
    private int statusCode;

    @Data
    public class Book {
        private long id;
        private String bookTitle;
        private Author author;
    }
}
