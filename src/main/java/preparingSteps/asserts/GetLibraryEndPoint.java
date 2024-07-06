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
}
