package preparingSteps.dataBase;

import entity.Author;
import entity.Book;
import model.requests.RequestPostNewAuthor;
import model.requests.RequestPostNewBook;
import model.responses.ResponseGetAuthorBooksXML;
import model.responses.ResponseGetAuthorsBooks;
import model.responses.ResponsePostNewAuthor;
import model.responses.ResponsePostNewBook;
import preparingSteps.requests.RequestSender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class GenerateTestData {
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

    public static long getBookId(Author author, String bookTitle) {
        RequestPostNewBook request = new RequestPostNewBook(bookTitle, author);
        ResponsePostNewBook response = RequestSender.responsePostBook(request);

        return response.getBookId()+1;
    }

    public static ResponseGetAuthorsBooks.Book generateBookForAuthor(Author author) {
        String bookTitle = generateBookTitle();
        long id = getBookId(author, bookTitle);

        return new ResponseGetAuthorsBooks.Book(id, bookTitle, author, author.getBirthDate());
    }

    public static Author generateNewUnregisteredAuthor() {
        Author author = new Author();
        author.setId(generateNewAuthor().getId() + 1);

        return author;
    }
}
