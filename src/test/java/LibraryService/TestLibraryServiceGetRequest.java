package LibraryService;

import Entity.Author;
import Entity.Book;
import Model.Requests.RequestGetAuthorBooksXML;
import Model.Requests.RequestGetAuthorsBooks;
import Model.Responses.ResponseGetAuthorsBooks;
import PreparingSteps.Requests.RequestSender;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static LibraryService.GenerateTestData.generateBookForAuthor;
import static LibraryService.GenerateTestData.generateNewAuthor;
import static junit.framework.Assert.assertEquals;

@Epic("Получение статус кодов на запрос GET")
@Story("Проверяются статус коды 200 и 400")
public class TestLibraryServiceGetRequest {
    private final int STATUS_CODE_FOR_SUCCESS_GET = 200;
    private final int STATUS_CODE_FOR_INCORRECT_GET = 400;
    private final int STATUS_CODE_FOR_NULL_GET = 400;

    @Test
    @DisplayName("Статус-код при GET запросе с существующим Author id c книгами")
    @Description("Проверка того что при вводе существующего author id с книгами в get запрос будет выдаваться 200 статус-код")
    public void testStatusCodeGetAuthorsBooksWithCorrectAuthorIdWithBooks() {
        Author author = generateNewAuthor();
        Book book = generateBookForAuthor(author);
        Response response = RequestSender.responseGetBooks(new RequestGetAuthorsBooks(author.getId()));

        assertEquals(STATUS_CODE_FOR_SUCCESS_GET, response.getStatusCode());
    }
    @Test
    @DisplayName("Статус-код при GET запросе с некорректным Author id")
    @Description("Проверка того что при вводе несуществующего author id в get запрос будет выдаваться 400 статус-код")
    public void testStatusCodeGetAuthorsBooksWithIncorrectAuthorId() {
        Author author = new Author();
        Response response = RequestSender.responseGetBooks(new RequestGetAuthorsBooks(author.getId()));
        assertEquals(STATUS_CODE_FOR_INCORRECT_GET, response.getStatusCode());
    }
    @Test
    @DisplayName("Статус-код при GET запросе с отсутствием ввода обязательного параметра")
    @Description("Проверка того что при отсутствии ввода author id в get запрос будет выдаваться 400 статус-код")
    public void testStatusCodeGetAuthorBooksWithNullAuthorId() {
        Response response = RequestSender.responseGetBooks(new RequestGetAuthorsBooks());
        assertEquals(STATUS_CODE_FOR_NULL_GET, response.getStatusCode());
    }
    @Test
    @DisplayName("Статус-код при GET запросе xml формата, существующим author id")
    @Description("Проверка того что при вводе существующего author id в get запрос xml формата будет выдаваться 200 статус-код")
    public void testStatusCodeGetAuthorsBooksXmlWiltCorrectAuthorId() {
        Author author = generateNewAuthor();
        Book book = generateBookForAuthor(author);
        Response response = RequestSender.responseGetBooksXml(new RequestGetAuthorBooksXML(author));
        assertEquals(STATUS_CODE_FOR_SUCCESS_GET, response.getStatusCode());
    }
    @Test
    @DisplayName("Статус-код при GET запросе xml формата, несуществующим author id")
    @Description("Проверка того что при вводе несуществующего author id в get запрос xml формата будет выдаваться 400 статус-код")
    public void testStatusCodeGetAuthorsBooksXmlWithIncorrectAuthorId() {
        Author author = new Author();
        Response response = RequestSender.responseGetBooksXml(new RequestGetAuthorBooksXML(author));
        assertEquals(STATUS_CODE_FOR_INCORRECT_GET, response.getStatusCode());
    }
    @Test
    @DisplayName("Статус-код при GET запросе xml формата, без ввода обязательного параметра")
    @Description("Проверка того что при отсутствии ввода author id в get запрос xml формата будет выдаваться 400 статус-код")
    public void testStatusCodeGetAuthorBooksXmlWithNullAuthorId() {
        Response response = RequestSender.responseGetBooks(new RequestGetAuthorsBooks());
        assertEquals(STATUS_CODE_FOR_NULL_GET, response.getStatusCode());
    }
    @Test
    @DisplayName("Статус-код при GET запросе с существующим Author id без книг")
    @Description("Проверка того что при вводе существующего author id без книг в get запрос будет выдаваться 200 статус-код")
    public void testStatusCodeGetAuthorsBooksWithCorrectAuthorIdWithoutBooks() {
        Author author = generateNewAuthor();
        Response response = RequestSender.responseGetBooks(new RequestGetAuthorsBooks(author.getId()));

        assertEquals(STATUS_CODE_FOR_SUCCESS_GET, response.getStatusCode());
    }
}
