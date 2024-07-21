package preparingSteps.asserts;

import entity.Book;
import io.restassured.response.Response;
import model.responses.ResponseGetAuthorsBooks;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;

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

    public static void checkTemplate(List<String> updatedList) {
        List<String> filteredList = updatedList.stream()
                .filter(x -> x.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d+\\+00:00"))
                .toList();
        assertEquals(updatedList, filteredList);
    }
    public static void checkNumberOfBooks(int expected, List<Book> books) {
        assertEquals(expected, books.size());
    }
}
