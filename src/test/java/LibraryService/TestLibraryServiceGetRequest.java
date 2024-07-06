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

import static preparingSteps.asserts.GetLibraryEndPoint.checkStatusCode;
import static preparingSteps.asserts.GetLibraryEndPoint.checkStatusCodeXml;
import static preparingSteps.dataBase.GenerateTestData.generateBookForAuthor;
import static preparingSteps.dataBase.GenerateTestData.generateNewAuthor;
import static junit.framework.Assert.assertEquals;

@Epic("Получение статус кодов на запрос GET")
@Story("Проверяются успешные GET запросы")
public class TestLibraryServiceGetRequest {
    private final int STATUS_CODE_FOR_SUCCESS_GET = 200;

    @Test
    @DisplayName("Статус-код при GET запросе с существующим Author id")
    @Description("Получен код 200 с одной книгой")
    public void testStatusCodeGetAuthorsBooksWithCorrectAuthorId() {
        Author author = generateNewAuthor();
        Book book = generateBookForAuthor(author);
        List<ResponseGetAuthorsBooks> actual = RequestSender
                .responseGetBooks(new RequestGetAuthorsBooks(author.getId()));

        checkStatusCode(STATUS_CODE_FOR_SUCCESS_GET, actual);
    }

    @Test
    @DisplayName("Статус-код при GET запросе xml формата, существующим author id")
    @Description("Получен код 200 с одной книгой в xml формате")
    public void testStatusCodeGetAuthorsBooksXmlWiltCorrectAuthorId() {
        Author author = generateNewAuthor();
        Book book = generateBookForAuthor(author);
        ResponseGetAuthorBooksXML actual = RequestSender.responseGetBooksXml(new RequestGetAuthorBooksXML(author));

        checkStatusCodeXml(STATUS_CODE_FOR_SUCCESS_GET, actual);
    }
}
