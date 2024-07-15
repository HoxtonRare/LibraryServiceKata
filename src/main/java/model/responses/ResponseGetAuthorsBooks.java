package model.responses;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import entity.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseGetAuthorsBooks {
    private int errorCode;
    private String errorMessage;
    private String errorDetails;
    @JsonUnwrapped
    private Book book;
    private int statusCode;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Book {
        private long id;
        private String bookTitle;
        private Author author;
        private String updated;
    }
}
