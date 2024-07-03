package Model.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponsePostNewBook {
    private long bookId;
    private int errorCode;
    private String errorMessage;
    private String errorDetails;
}
