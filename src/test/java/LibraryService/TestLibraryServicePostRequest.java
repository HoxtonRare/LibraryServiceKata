package LibraryService;

import entity.Author;
import model.requests.RequestPostNewBook;
import model.responses.ResponsePostNewAuthor;
import model.responses.ResponsePostNewBook;
import preparingSteps.requests.RequestSender;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static LibraryService.StatusCodesForTests.*;
import static preparingSteps.asserts.GetLibraryEndPoint.checkResponseBody;
import static preparingSteps.asserts.PostLibraryEndPoint.*;
import static preparingSteps.dataBase.GenerateTestData.*;
import static junit.framework.Assert.assertEquals;

@Epic("Получение статус кодов на запрос POST")
@Story("Проверяются статус коды 200, 400 и 409")
public class TestLibraryServicePostRequest {

    @Test
    @DisplayName("Статус-код, при добавлении новой книги с существующим Author id")
    @Description("Получен код 201, книга добавлена")
    public void testStatusCodePutAuthorsBookWithCorrectData() {
        Author author = generateNewAuthor();
        String bookTitle = generateBookTitle();

        ResponsePostNewBook expected = new ResponsePostNewBook();
        expected.setStatusCode(STATUS_CODE_FOR_SUCCESS_POST);
        expected.setBookId(getBookId(author, bookTitle));

        ResponsePostNewBook actual = RequestSender.responsePostBook(new RequestPostNewBook(bookTitle, author));



        checkStatusCodePostBook(STATUS_CODE_FOR_SUCCESS_POST, actual);
        checkResponseBody(expected, actual);
    }

    @Test
    @DisplayName("Статус-код при добавлении новой книги без ввода обязательных параметров")
    @Description("Получен код 400. Сервис возвращает код ошибки 1001")
    public void testStatusCodePutAuthorsBookWithNullData() {
        RequestPostNewBook request = new RequestPostNewBook();
        Response response = RequestSender.getResponseForRequestPostBook(request);

        checkErrorResponseBody(response, ERROR_CODE_FOR_NULL_POST, ERROR_MESSAGE_FOR_NULL_TITLE_POST,
                STATUS_CODE_FOR_NULL_POST, ERROR_DETAILS_FOR_NULL_POST);
    }

    @Test
    @DisplayName("Статус-код при добавлении новой книги с несуществующим Author id")
    @Description("Получен код 409. Сервис возвращает код ошибки 1004 с описанием: “Указанный автор не существует в таблице”")
    public void testStatusCodePutAuthorsBookWithIncorrectAuthor() {
        Author author = new Author();
        String title = generateBookTitle();
        RequestPostNewBook request = new RequestPostNewBook(title, author);
        Response response = RequestSender.getResponseForRequestPostBook(request);

        checkErrorResponseBody(response, ERROR_CODE_FOR_INCORRECT_POST, ERROR_MESSAGE_FOR_INCORRECT_POST,
                STATUS_CODE_FOR_INCORRECT_POST, null);
    }

    @Test
    @DisplayName("Статус-код при добавлении новой книги без ввода author")
    @Description("Получен код 400. Сервис возвращает код ошибки 1001 с описанием: “Не передан обязательный параметр: author")
    public void testStatusCodePutAuthorsBooksWithNullAuthor() {
        String title = generateBookTitle();
        RequestPostNewBook request = new RequestPostNewBook(title);
        Response response = RequestSender.getResponseForRequestPostBook(request);

        checkErrorResponseBody(response, ERROR_CODE_FOR_NULL_POST,
                ERROR_MESSAGE_FOR_NULL_AUTHOR_POST,STATUS_CODE_FOR_NULL_POST, ERROR_DETAILS_FOR_NULL_POST);
    }

    @Test
    @DisplayName("Статус-код при добавлении новой книги без ввода bookTitle")
    @Description("Получен код 400. Сервис возвращает код ошибки 1001 с описанием: “Не передан обязательный параметр: bookTitle")
    public void testStatusCodePutAuthorsBooksWithNullBookTitle() {
        Author author = generateNewAuthor();
        RequestPostNewBook request = new RequestPostNewBook(author);
        Response response = RequestSender.getResponseForRequestPostBook(request);

        checkErrorResponseBody(response, ERROR_CODE_FOR_NULL_POST,
                ERROR_MESSAGE_FOR_NULL_TITLE_POST,STATUS_CODE_FOR_NULL_POST, ERROR_DETAILS_FOR_NULL_POST);
    }
}
