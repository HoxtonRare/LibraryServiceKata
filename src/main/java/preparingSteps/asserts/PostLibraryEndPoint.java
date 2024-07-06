package preparingSteps.asserts;

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

    public static void checkStatusCodePostAuthor(int expected, ResponsePostNewAuthor actual) {
        assertEquals(expected, actual.getStatusCode());
    }

    public static void checkErrorCodePostBook(int expected, ResponsePostNewBook actual) {
        assertEquals(expected, actual.getErrorCode());
    }

    public static void checkErrorMessagePostBook(String expected, ResponsePostNewBook actual) {
        assertEquals(expected, actual.getErrorMessage());
    }

    public static void checkErrorDetailsPostBook(String expected, ResponsePostNewBook actual) {
        assertEquals(expected, actual.getErrorDetails());
    }

    public static void checkErrorResponseBody(RequestPostNewBook request, String errorCode, String errorMessage, int statusCode, String errorDetails) {
        given()
                .spec(RequestBuilder.postAuthorBookSpec(request))
                .when()
                .post()
                .then()
                .statusCode(statusCode)
                .body("errorCode", equalTo(errorCode))
                .body("errorMessage", equalTo(errorMessage))
                .body("errorDetails", equalTo(errorDetails));
    }
}
