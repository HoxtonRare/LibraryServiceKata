package LibraryService;

import entity.Author;
import entity.Book;
import model.requests.RequestGetAuthorBooksXML;
import model.requests.RequestGetAuthorsBooks;
import preparingSteps.requests.RequestSender;
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
@Story("Проверяются успешные GET запросы")
public class TestLibraryServiceGetRequest {
    private final int STATUS_CODE_FOR_SUCCESS_GET = 200;

    @Test
    @DisplayName("Статус-код при GET запросе с существующим Author id")
    @Description("Проверка того, что при вводе существующего author id в get запрос, будет выдаваться 200 статус-код")
    public void testStatusCodeGetAuthorsBooksWithCorrectAuthorId() {
        Author author = generateNewAuthor();
        Book book = generateBookForAuthor(author);
        Response response = RequestSender.responseGetBooks(new RequestGetAuthorsBooks(author.getId()));

        assertEquals(STATUS_CODE_FOR_SUCCESS_GET, response.getStatusCode());
    }

    @Test
    @DisplayName("Статус-код при GET запросе xml формата, существующим author id")
    @Description("Проверка того, что при вводе существующего author id в get запрос xml формата, будет выдаваться 200 статус-код")
    public void testStatusCodeGetAuthorsBooksXmlWiltCorrectAuthorId() {
        Author author = generateNewAuthor();
        Book book = generateBookForAuthor(author);
        Response response = RequestSender.responseGetBooksXml(new RequestGetAuthorBooksXML(author));
        assertEquals(STATUS_CODE_FOR_SUCCESS_GET, response.getStatusCode());
    }
}
