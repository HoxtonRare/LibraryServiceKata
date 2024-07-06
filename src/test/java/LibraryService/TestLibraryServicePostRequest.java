package LibraryService;

import entity.Author;
import model.requests.RequestPostNewBook;
import model.responses.ResponsePostNewBook;
import preparingSteps.requests.RequestSender;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static preparingSteps.asserts.PostLibraryEndPoint.checkStatusCodePostBook;
import static preparingSteps.dataBase.GenerateTestData.*;
import static junit.framework.Assert.assertEquals;

@Epic("Получение статус кодов на запрос POST")
@Story("Проверяется успешный POST запрос")
public class TestLibraryServicePostRequest {
    private final int STATUS_CODE_FOR_SUCCESS_POST = 201;

    @Test
    @DisplayName("Статус-код, при добавлении новой книги с существующим Author id")
    @Description("Получен код 201, книга добавлена")
    public void testStatusCodePutAuthorsBookWithCorrectData() {
        Author author = generateNewAuthor();
        String bookTitle = generateBookTitle();
        ResponsePostNewBook actual = RequestSender.responsePostBook(new RequestPostNewBook(bookTitle, author));

        checkStatusCodePostBook(STATUS_CODE_FOR_SUCCESS_POST, actual);
    }
}
