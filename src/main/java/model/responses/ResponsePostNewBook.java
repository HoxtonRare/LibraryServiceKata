package model.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponsePostNewBook {
    private long bookId;
    private int errorCode;
    private String errorMessage;
    private String errorDetails;
}
