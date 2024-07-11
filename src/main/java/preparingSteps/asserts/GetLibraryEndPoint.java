package preparingSteps.asserts;

import io.restassured.response.Response;
import model.requests.RequestGetAuthorBooksXML;
import model.requests.RequestGetAuthorsBooks;
import model.responses.ResponseGetAuthorBooksXML;
import model.responses.ResponseGetAuthorsBooks;
import preparingSteps.requests.RequestBuilder;

import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class GetLibraryEndPoint {
    public static void checkStatusCode(int expected, List<ResponseGetAuthorsBooks> actual) {
        assertEquals(expected, actual.getFirst().getStatusCode());
    }

    public static <T> void checkResponseBody(T expected, T actual) {
        assertEquals(expected, actual);
    }

    public static void checkErrorResponseBody(Response response, String errorCode, String errorMessage, int statusCode) {
        response
                .then()
                .statusCode(statusCode)
                .body("errorCode", equalTo(errorCode))
                .body("errorMessage", equalTo(errorMessage));
    }
}
