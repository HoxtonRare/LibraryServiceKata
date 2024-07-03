package Model.Requests;

import PreparingSteps.Requests.RequestSender;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestGetAuthorsBooks {
    private long id;
}
