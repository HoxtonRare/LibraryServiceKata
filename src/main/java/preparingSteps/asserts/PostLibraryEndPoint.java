package preparingSteps.asserts;

import io.restassured.response.Response;
import model.requests.RequestGetAuthorsBooks;
import model.requests.RequestPostNewBook;
import model.responses.ResponsePostNewAuthor;
import model.responses.ResponsePostNewBook;
import preparingSteps.requests.RequestBuilder;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

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
