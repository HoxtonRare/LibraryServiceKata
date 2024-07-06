package preparingSteps.asserts;

import model.responses.ResponsePostNewAuthor;
import model.responses.ResponsePostNewBook;

import static junit.framework.Assert.assertEquals;

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
}
