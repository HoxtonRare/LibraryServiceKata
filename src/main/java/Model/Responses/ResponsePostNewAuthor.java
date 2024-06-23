package Model.Responses;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponsePostNewAuthor {
    private long authorsId;
    private int errorCode;
    private String errorMessage;
    private String errorDetails;
}
