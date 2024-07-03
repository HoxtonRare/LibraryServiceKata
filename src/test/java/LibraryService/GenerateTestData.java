package LibraryService;

import Entity.Author;
import Entity.Book;
import Model.Requests.RequestGetAuthorsBooks;
import Model.Requests.RequestPostNewAuthor;
import Model.Requests.RequestPostNewBook;
import Model.Responses.ResponseGetAuthorsBooks;
import Model.Responses.ResponsePostNewAuthor;
import Model.Responses.ResponsePostNewBook;
import PreparingSteps.Requests.RequestSender;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class GenerateTestData {
    public static Author generateNewAuthor() {
        String firstName = randomAlphabetic(5, 15);
        String familyName = randomAlphabetic(5, 15);
        String secondName = randomAlphabetic(5, 15);
        long id = RequestSender.ResponsePostAuthor(new RequestPostNewAuthor(firstName, familyName, secondName))
                .as(ResponsePostNewAuthor.class).getAuthorId();
        return new Author(id, firstName, familyName, secondName);
    }

    public static String generateBookTitle() {
        return randomAlphabetic(5, 20);
    }

    public static long getBookId(Author author, String bookTitle) {
        RequestPostNewBook request = new RequestPostNewBook(bookTitle, author);
        ResponsePostNewBook response = RequestSender.ResponsePostBook(request).as(ResponsePostNewBook.class);
        return response.getBookId()+1;
    }

    public static Book generateBookForAuthor(Author author) {
        String bookTitle = generateBookTitle();
        long id = getBookId(author, bookTitle);
        return new Book(id, bookTitle, author);
    }
}
