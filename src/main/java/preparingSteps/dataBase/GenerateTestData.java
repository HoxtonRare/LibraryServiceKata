package preparingSteps.dataBase;

import entity.Author;
import entity.Book;
import model.requests.RequestPostNewAuthor;
import model.requests.RequestPostNewBook;
import preparingSteps.requests.RequestSender;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class GenerateTestData {

    private static final ExecutionRequest executionRequest = new ExecutionRequest();

    public static Author generateNewAuthor() {
        String firstName = randomAlphabetic(5, 15);
        String familyName = randomAlphabetic(5, 15);
        String secondName = randomAlphabetic(5, 15);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthDate = formatter.format(LocalDate.now());
        long id = RequestSender.responsePostAuthor(new RequestPostNewAuthor(firstName, familyName, secondName, birthDate))
                .getAuthorId();

        return new Author(id, firstName, familyName, secondName, birthDate);
    }

    public static String generateBookTitle() {
        return randomAlphabetic(5, 20);
    }

    public static long getBookId(Author author, String bookTitle, Timestamp updated) {
        RequestPostNewBook request = new RequestPostNewBook(bookTitle, author);
        executionRequest.insertBook(bookTitle, author.getId(), updated);
        List<Book> books = executionRequest.findBookByTitle(bookTitle);

        return books.getFirst().getId()+1;
    }

    public static Author generateNewUnregisteredAuthor() {
        Author author = new Author();
        author.setId(generateNewAuthor().getId() + 1);

        return author;
    }

    public static List<Book> generateExpectedList(long bookId, String bookTitle, long authorId, Timestamp updated) {
        List<Book> expected = new ArrayList<>();
        expected.addFirst(new Book(bookId-1, bookTitle, authorId, updated));
        return expected;
    }
}
