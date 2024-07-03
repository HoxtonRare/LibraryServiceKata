package model.responses;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponsePostNewAuthor {
    private long authorId;
    private int errorCode;
    private String errorMessage;
    private String errorDetails;
}
