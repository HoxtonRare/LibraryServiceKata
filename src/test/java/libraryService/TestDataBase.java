package libraryService;

import entity.Book;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import preparingSteps.dataBase.ExecutionRequest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static preparingSteps.asserts.GetLibraryEndPoint.*;
import static preparingSteps.dataBase.GenerateTestData.*;

@Epic("Работа с базой данных")
@Story("Проверяются основные команды")
public class TestDataBase {

    @Test
    @DisplayName("Добавление и удаление записей из таблицы")
    @Description("Сначала получены 2 добавленные книги, потом одна по названию и, после удаления второй, снова получена 1 книга")
    public void CheckBookTable() {
        ExecutionRequest executionRequest = new ExecutionRequest();

        executionRequest.deleteAll();

        String bookTitle1 = generateBookTitle();
        String bookTitle2 = generateBookTitle();
        Timestamp updated = Timestamp.valueOf(LocalDateTime.now());
        executionRequest.insertBook(bookTitle1, (long) 1, updated);
        executionRequest.insertBook(bookTitle2, (long) 2, updated);

        List<Book> books = executionRequest.findAll();
        System.out.println(books);
        checkNumberOfBooks(2, books);

        List<Book> foundBook = executionRequest.findBookByTitle(bookTitle1);
        System.out.println(foundBook);
        checkNumberOfBooks(1, foundBook);

        executionRequest.deleteBookByTitle(bookTitle2);
        List<Book> lastBook = executionRequest.findAll();
        System.out.println(lastBook);
        checkNumberOfBooks(1, lastBook);
    }
}
