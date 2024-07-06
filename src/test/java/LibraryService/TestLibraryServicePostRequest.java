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

import static preparingSteps.asserts.PostLibraryEndPoint.*;
import static preparingSteps.dataBase.GenerateTestData.*;
import static junit.framework.Assert.assertEquals;

@Epic("Получение статус кодов на запрос POST")
@Story("Проверяются статус коды 200, 400 и 409")
public class TestLibraryServicePostRequest {
    private final int STATUS_CODE_FOR_SUCCESS_POST = 201;
    private final int STATUS_CODE_FOR_NULL_POST = 400;
    private final int STATUS_CODE_FOR_INCORRECT_POST = 409;
    private final int ERROR_CODE_FOR_INCORRECT_POST = 1004;
    private final int ERROR_CODE_FOR_NULL_POST = 1001;
    private final String ERROR_MESSAGE_FOR_INCORRECT_POST = "Указанный автор не существует в таблице";
    private final String ERROR_MESSAGE_FOR_NULL_TITLE_POST = "Не передан обязательный параметр: bookTitle";


    @Test
    @DisplayName("Статус-код, при добавлении новой книги с существующим Author id")
    @Description("Получен код 201, книга добавлена")
    public void testStatusCodePutAuthorsBookWithCorrectData() {
        Author author = generateNewAuthor();
        String bookTitle = generateBookTitle();
        ResponsePostNewBook actual = RequestSender.responsePostBook(new RequestPostNewBook(bookTitle, author));

        checkStatusCodePostBook(STATUS_CODE_FOR_SUCCESS_POST, actual);
    }

    @Test
    @DisplayName("Статус-код при добавлении новой книги без ввода обязательных параметров")
    @Description("Получен код 400. Сервис возвращает код ошибки 1001")
    public void testStatusCodePutAuthorsBookWithNullData() {
        ResponsePostNewBook actual = RequestSender.responsePostBook(new RequestPostNewBook());

        checkStatusCodePostBook(STATUS_CODE_FOR_NULL_POST, actual);
        checkErrorCodePostBook(ERROR_CODE_FOR_NULL_POST, actual);
        checkErrorMessagePostBook(ERROR_MESSAGE_FOR_NULL_TITLE_POST, actual);
    }

    @Test
    @DisplayName("Статус-код при добавлении новой книги с несуществующим Author id")
    @Description("Получен код 409. Сервис возвращает код ошибки 1004 с описанием: “Указанный автор не существует в таблице”")
    public void testStatusCodePutAuthorsBookWithIncorrectAuthor() {
        Author author = new Author();
        String title = generateBookTitle();
        ResponsePostNewBook actual = RequestSender.responsePostBook(new RequestPostNewBook(title, author));

        checkStatusCodePostBook(STATUS_CODE_FOR_INCORRECT_POST, actual);
        checkErrorCodePostBook(ERROR_CODE_FOR_INCORRECT_POST, actual);
        checkErrorMessagePostBook(ERROR_MESSAGE_FOR_INCORRECT_POST, actual);
    }
}
