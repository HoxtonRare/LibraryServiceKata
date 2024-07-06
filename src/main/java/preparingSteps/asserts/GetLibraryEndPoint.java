package preparingSteps.asserts;

import io.restassured.response.Response;
import model.responses.ResponseGetAuthorBooksXML;
import model.responses.ResponseGetAuthorsBooks;

import java.util.List;

import static junit.framework.Assert.assertEquals;

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
}
