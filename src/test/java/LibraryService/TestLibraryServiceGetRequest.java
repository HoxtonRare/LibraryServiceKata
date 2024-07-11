package LibraryService;

import entity.Author;
import entity.Book;
import model.requests.RequestGetAuthorBooksXML;
import model.requests.RequestGetAuthorsBooks;
import model.responses.ResponseGetAuthorBooksXML;
import model.responses.ResponseGetAuthorsBooks;
import model.responses.ResponsePostNewBook;
import preparingSteps.requests.RequestSender;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static LibraryService.StatusCodesForTests.*;
import static preparingSteps.asserts.GetLibraryEndPoint.*;
import static preparingSteps.dataBase.GenerateTestData.*;

@Epic("Получение статус кодов на запрос GET")
@Story("Проверяются статус коды 200 и 400")
public class TestLibraryServiceGetRequest {

    @Test
    @DisplayName("Статус-код при GET запросе с существующим Author id. Позитивный кейс")
    @Description("Получен код 200 с одной книгой")
    public void testStatusCodeGetAuthorsBooksWithCorrectAuthorId() {
        Author author = generateNewAuthor();
        String bookTitle = generateBookTitle();
        long bookId = getBookId(author, bookTitle);

        ResponseGetAuthorsBooks expected = new ResponseGetAuthorsBooks();
        expected.setBook(new ResponseGetAuthorsBooks.Book(bookId, bookTitle, author, author.getBirthDate()));
        expected.setStatusCode(200);

        List<ResponseGetAuthorsBooks> actual = RequestSender
                .responseGetBooks(new RequestGetAuthorsBooks(author.getId()));
        List<String> listForCheckTemplate = new ArrayList<>();
        actual.forEach(x -> listForCheckTemplate.add(x.getBook().getUpdated()));
        actual.forEach(x -> x.getBook().setUpdated(null));

        checkTemplate(listForCheckTemplate);
        checkResponseBody(expected, actual);
    }

    @Test
    @DisplayName("Статус-код при GET запросе с существующим Author id без книг. Позитивный кейс")
    @Description("Получен код 200 с пустым списком книг")
    public void testStatusCodeGetAuthorsBooksWithCorrectAuthorIdWithoutBooks() {
        Author author = generateNewAuthor();
        List<ResponseGetAuthorsBooks> actual = RequestSender
                .responseGetBooks(new RequestGetAuthorsBooks(author.getId()));
        ResponseGetAuthorsBooks expected = new ResponseGetAuthorsBooks();
        expected.setStatusCode(STATUS_CODE_FOR_SUCCESS_GET);

        checkStatusCode(STATUS_CODE_FOR_SUCCESS_GET, actual);
    }

    @Test
    @DisplayName("Статус-код при GET запросе xml формата, существующим author id. Позитивный кейс")
    @Description("Получен код 200 с одной книгой в xml формате")
    public void testStatusCodeGetAuthorsBooksXmlWiltCorrectAuthorId() {
        Author author = generateNewAuthor();
        String bookTitle = generateBookTitle();
        long bookId = getBookId(author, bookTitle);
        List <ResponseGetAuthorBooksXML.Book> expectedList = new ArrayList<>();
        expectedList.add(new ResponseGetAuthorBooksXML.Book(bookId, bookTitle, author, author.getBirthDate()));
        ResponseGetAuthorBooksXML expected = new ResponseGetAuthorBooksXML();
        expected.setBooks(expectedList);

        ResponseGetAuthorBooksXML actual = RequestSender.responseGetBooksXml(new RequestGetAuthorBooksXML(author));
        expected.setStatusCode(STATUS_CODE_FOR_SUCCESS_GET);
        List<String> listForCheckTemplate = new ArrayList<>();
        actual.getBooks().forEach(x -> listForCheckTemplate.add(x.getUpdated()));
        actual.getBooks().forEach(x -> x.setUpdated(null));

        System.out.println(listForCheckTemplate);
        checkTemplate(listForCheckTemplate);
        checkResponseBody(expected, actual);
    }

    @Test
    @DisplayName("Статус-код при GET запросе с некорректным Author id. Негативный кейс")
    @Description("Получен код 400. Сервис возвращает код ошибки 1004 с описанием: “Указанный автор не существует в таблице”")
    public void testStatusCodeGetAuthorsBooksWithIncorrectAuthorId() {
        Author author = generateNewUnregisteredAuthor();
        RequestGetAuthorsBooks request = new RequestGetAuthorsBooks(author.getId());
        Response response = RequestSender.getResponseForRequestGetBooks(request);

        checkErrorResponseBody(response, ERROR_CODE_FOR_INCORRECT_GET, ERROR_MESSAGE_FOR_INCORRECT_GET, STATUS_CODE_FOR_INCORRECT_GET);
    }

    @Test
    @DisplayName("Статус-код при GET запросе с отсутствием ввода обязательного параметра. Негативный кейс")
    @Description("Получен код 400. сервис возвращает код ошибки 1001")
    public void testStatusCodeGetAuthorBooksWithNullAuthorId() {
        RequestGetAuthorsBooks request = new RequestGetAuthorsBooks();
        Response response = RequestSender.getResponseForRequestGetBooks(request);


        checkErrorResponseBody(response, ERROR_CODE_FOR_NULL_GET, ERROR_MESSAGE_FOR_NULL_GET, STATUS_CODE_FOR_NULL_GET);
    }

    @Test
    @DisplayName("Статус-код при GET запросе xml формата, несуществующим author id. Негативный кейс")
    @Description("Получен код 400. Сервис возвращает код ошибки 1004 с описанием: “Указанный автор не существует в таблице”")
    public void testStatusCodeGetAuthorsBooksXmlWithIncorrectAuthorId() {
        Author author = generateNewUnregisteredAuthor();
        RequestGetAuthorBooksXML request = new RequestGetAuthorBooksXML(author);
        Response response = RequestSender.getResponseForRequestGetBooksXml(request);


        checkErrorResponseBody(response, ERROR_CODE_FOR_INCORRECT_GET, ERROR_MESSAGE_FOR_INCORRECT_GET, STATUS_CODE_FOR_INCORRECT_GET);
    }

    @Test
    @DisplayName("Статус-код при GET запросе xml формата, c пустым author id. Негативный кейс")
    @Description("Получен код 400. сервис возвращает код ошибки 1001")
    public void testStatusCodeGetAuthorBooksXmlWithNullAuthorId() {
        Author author = new Author();
        RequestGetAuthorBooksXML request = new RequestGetAuthorBooksXML(author);
        Response response = RequestSender.getResponseForRequestGetBooksXml(request);


        checkErrorResponseBody(response, ERROR_CODE_FOR_NULL_GET, ERROR_MESSAGE_FOR_NULL_GET, STATUS_CODE_FOR_NULL_GET);
    }

}
