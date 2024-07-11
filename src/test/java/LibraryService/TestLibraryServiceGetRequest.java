package LibraryService;

import entity.Author;
import entity.Book;
import model.requests.RequestGetAuthorBooksXML;
import model.requests.RequestGetAuthorsBooks;
import model.responses.ResponseGetAuthorBooksXML;
import model.responses.ResponseGetAuthorsBooks;
import preparingSteps.requests.RequestSender;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static LibraryService.StatusCodesForTest.*;
import static junit.framework.Assert.assertEquals;
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
        Book book = generateBookForAuthor(author);
        List<ResponseGetAuthorsBooks> actual = RequestSender
                .responseGetBooks(new RequestGetAuthorsBooks(author.getId()));

        checkStatusCode(STATUS_CODE_FOR_SUCCESS_GET, actual);
    }

    @Test
    @DisplayName("Статус-код при GET запросе с существующим Author id без книг. Позитивный кейс")
    @Description("Получен код 200 с пустым списком книг")
    public void testStatusCodeGetAuthorsBooksWithCorrectAuthorIdWithoutBooks() {
        Author author = generateNewAuthor();
        List<ResponseGetAuthorsBooks> actual = RequestSender
                .responseGetBooks(new RequestGetAuthorsBooks(author.getId()));

        checkStatusCode(STATUS_CODE_FOR_SUCCESS_GET, actual);
    }

    @Test
    @DisplayName("Статус-код при GET запросе xml формата, существующим author id. Позитивный кейс")
    @Description("Получен код 200 с одной книгой в xml формате")
    public void testStatusCodeGetAuthorsBooksXmlWiltCorrectAuthorId() {
        Author author = generateNewAuthor();
        Book book = generateBookForAuthor(author);
        ResponseGetAuthorBooksXML actual = RequestSender.responseGetBooksXml(new RequestGetAuthorBooksXML(author));

        checkStatusCodeXml(STATUS_CODE_FOR_SUCCESS_GET, actual);
    }

    @Test
    @DisplayName("Статус-код при GET запросе с некорректным Author id. Негативный кейс")
    @Description("Получен код 400. Сервис возвращает код ошибки 1004 с описанием: “Указанный автор не существует в таблице”")
    public void testStatusCodeGetAuthorsBooksWithIncorrectAuthorId() {
        Author author = generateNewUnregisteredAuthor();
        List<ResponseGetAuthorsBooks> actual = RequestSender
                .responseGetBooks(new RequestGetAuthorsBooks(author.getId()));

        checkErrorCode(ERROR_CODE_FOR_INCORRECT_GET, actual);
        checkErrorMessage(ERROR_MESSAGE_FOR_INCORRECT_GET, actual);
        checkStatusCode(STATUS_CODE_FOR_INCORRECT_GET, actual);

    }

    @Test
    @DisplayName("Статус-код при GET запросе с отсутствием ввода обязательного параметра. Негативный кейс")
    @Description("Получен код 400. сервис возвращает код ошибки 1001")
    public void testStatusCodeGetAuthorBooksWithNullAuthorId() {
        List<ResponseGetAuthorsBooks> actual = RequestSender
                .responseGetBooks(new RequestGetAuthorsBooks());
        System.out.println(actual.getFirst().getErrorMessage());

        checkErrorCode(ERROR_CODE_FOR_NULL_GET, actual);
        checkStatusCode(STATUS_CODE_FOR_NULL_GET, actual);
    }

    @Test
    @DisplayName("Статус-код при GET запросе xml формата, несуществующим author id. Негативный кейс")
    @Description("Получен код 400. Сервис возвращает код ошибки 1004 с описанием: “Указанный автор не существует в таблице”")
    public void testStatusCodeGetAuthorsBooksXmlWithIncorrectAuthorId() {
        Author author = generateNewUnregisteredAuthor();
        ResponseGetAuthorBooksXML actual = RequestSender
                .responseGetBooksXml(new RequestGetAuthorBooksXML(author));

        checkErrorCodeXml(STATUS_CODE_FOR_INCORRECT_GET, actual);
        checkErrorMessageXml(ERROR_MESSAGE_FOR_INCORRECT_GET, actual);
        checkStatusCodeXml(STATUS_CODE_FOR_INCORRECT_GET, actual);
    }
    @Test
    @DisplayName("Статус-код при GET запросе xml формата, c пустым author id. Негативный кейс")
    @Description("Получен код 400. сервис возвращает код ошибки 1001")
    public void testStatusCodeGetAuthorBooksXmlWithNullAuthorId() {
        Author author = new Author();
        ResponseGetAuthorBooksXML actual = RequestSender
                .responseGetBooksXml(new RequestGetAuthorBooksXML(author));

        checkErrorCodeXml(ERROR_CODE_FOR_INCORRECT_GET, actual);
        checkStatusCodeXml(STATUS_CODE_FOR_NULL_GET, actual);
    }
}
