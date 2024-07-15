package preparingSteps.asserts;

import io.restassured.response.Response;
import model.responses.ResponsePostNewBook;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;

public class PostLibraryEndPoint {
    public static void checkStatusCodePostBook(int expected, ResponsePostNewBook actual) {
        assertEquals(expected, actual.getStatusCode());
    }

    public static void checkErrorResponseBody(Response response, String errorCode, String errorMessage, int statusCode, String errorDetails) {
        response
                .then()
                .statusCode(statusCode)
                .body("errorCode", equalTo(errorCode))
                .body("errorMessage", equalTo(errorMessage))
                .body("errorDetails", equalTo(errorDetails));
    }
}
