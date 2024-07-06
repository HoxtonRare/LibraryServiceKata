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

    public static void checkStatusCodeXml(int expected, ResponseGetAuthorBooksXML actual) {
        assertEquals(expected, actual.getStatusCode());
    }

    public static void checkErrorCode(int expected, List<ResponseGetAuthorsBooks> actual) {
        assertEquals(expected, actual.getFirst().getErrorCode());
    }

    public static void checkErrorMessage(String expected, List<ResponseGetAuthorsBooks> actual) {
        assertEquals(expected, actual.getFirst().getErrorMessage());
    }

    public static void checkErrorCodeXml(int expected, ResponseGetAuthorBooksXML actual) {
        assertEquals(expected, actual.getErrorCode());
    }

    public static void checkErrorMessageXml(String expected, ResponseGetAuthorBooksXML actual) {
        assertEquals(expected, actual.getErrorMessage());
    }

    public static <T> void checkResponseBody(T expected, T actual) {
        assertEquals(expected, actual);
    }

    public static void checkErrorResponseBodyXml(RequestGetAuthorBooksXML request, String errorCode, String errorMessage, int statusCode) {
        given()
                .spec(RequestBuilder.getAuthorBooksXmlSpec(request))
                .when()
                .post()
                .then()
                .statusCode(statusCode)
                .body("errorCode", equalTo(errorCode))
                .body("$", hasKey("errorDetails"))
                .body("errorMessage", equalTo(errorMessage));
    }

    public static void checkErrorResponseBody(RequestGetAuthorsBooks request, String errorCode, String errorMessage, int statusCode) {
        given()
                .spec(RequestBuilder.getAuthorBooksSpec(request))
                .when()
                .get()
                .then()
                .statusCode(statusCode)
                .body("errorCode", equalTo(errorCode))
                .body("errorMessage", equalTo(errorMessage));
    }
}
