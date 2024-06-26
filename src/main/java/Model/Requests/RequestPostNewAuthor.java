package Model.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RequestPostNewAuthor {
    private String firstName;
    private String familyName;
    private String secondName;
}
