package model.requests;

import entity.Author;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPostNewBook {
    private String bookTitle;
    private Author author;

    public RequestPostNewBook(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public RequestPostNewBook(Author author) {
        this.author = author;
    }
}
