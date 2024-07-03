package LibraryService;

import Entity.Author;
import Model.Requests.RequestPostNewBook;
import PreparingSteps.Requests.RequestSender;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static LibraryService.GenerateTestData.*;
import static junit.framework.Assert.assertEquals;

@Epic("Получение статус кодов на запрос POST")
@Story("Проверяется успешный POST запрос")
public class TestLibraryServicePostRequest {
    private final int STATUS_CODE_FOR_SUCCESS_POST = 201;

    @Test
    @DisplayName("Статус-код, при добавлении новой книги с существующим Author id")
    @Description("Проверка того, что при вводе существующего author id и корректного bookTitle в post запрос, будет выдаваться 200 статус-код")
    public void testStatusCodePutAuthorsBookWithCorrectData() {
        Author author = generateNewAuthor();
        String bookTitle = generateBookTitle();

        Response response = RequestSender.ResponsePostBook(new RequestPostNewBook(bookTitle, author));
        assertEquals(STATUS_CODE_FOR_SUCCESS_POST, response.getStatusCode());
    }
}
