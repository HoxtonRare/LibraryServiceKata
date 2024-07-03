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
@Story("Проверяются статус коды 200, 400 и 409")
public class TestLibraryServicePostRequest {
    private final int STATUS_CODE_FOR_SUCCESS_POST = 201;
    private final int STATUS_CODE_FOR_NULL_POST = 400;
    private final int STATUS_CODE_FOR_INCORRECT_POST = 409;

    @Test
    @DisplayName("Статус-код при добавлении новой книги с существующим Author id")
    @Description("Проверка того что при вводе существующего author id в post запрос будет выдаваться 200 статус-код")
    public void testStatusCodePutAuthorsBookWithCorrectData() {
        Author author = generateNewAuthor();
        String bookTitle = generateBookTitle();

        Response response = RequestSender.ResponsePostBook(new RequestPostNewBook(bookTitle, author));
        assertEquals(STATUS_CODE_FOR_SUCCESS_POST, response.getStatusCode());
    }

    @Test
    @DisplayName("Статус-код при добавлении новой книги без ввода обязательных параметров")
    @Description("Проверка того что при отсутствии ввода author id и bookTitle в post запрос будет выдаваться 400 статус-код")
    public void testStatusCodePutAuthorsBookWithNullData() {
        Response response = RequestSender.ResponsePostBook(new RequestPostNewBook());
        assertEquals(STATUS_CODE_FOR_NULL_POST, response.getStatusCode());
    }

    @Test
    @DisplayName("Статус-код при добавлении новой книги с несуществующим Author id")
    @Description("Проверка того что при вводе несуществующего author id в post запрос будет выдаваться 409 статус-код")
    public void testStatusCodePutAuthorsBookWithIncorrectAuthor() {
        Author author = new Author();
        Response response = RequestSender.ResponsePostBook(new RequestPostNewBook("test", author));
        assertEquals(STATUS_CODE_FOR_INCORRECT_POST, response.getStatusCode());
    }
}
