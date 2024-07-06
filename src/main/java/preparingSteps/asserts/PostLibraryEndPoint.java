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
}
