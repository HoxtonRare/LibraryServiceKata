package Model.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponsePostNewBook {
    private long booksId;
    private int errorCode;
    private String errorMessage;
    private String errorDetails;
}
