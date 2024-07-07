package model.responses;

import entity.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "authors_books")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseGetAuthorBooksXML {
    private int errorCode;
    private String errorMessage;
    private String errorDetails;
    @XmlElement(name = "book")
    @XmlElementWrapper
    private List<Book> books;
    private int statusCode;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Book {
        private long id;
        @XmlElement(name = "book_title")
        private String bookTitle;
        private Author author;
        private String updated;
    }
}
