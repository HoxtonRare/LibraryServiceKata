package preparingSteps.requests;

import model.requests.RequestGetAuthorBooksXML;
import model.requests.RequestGetAuthorsBooks;
import model.requests.RequestPostNewAuthor;
import model.requests.RequestPostNewBook;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestSender {
    public static Response responseGetBooksXml(RequestGetAuthorBooksXML request) {
        return given()
                .spec(RequestBuilder.getAuthorBooksXmlSpec(request))
                .when()
                .post();
    }

    public static Response responseGetBooks(RequestGetAuthorsBooks request) {
        return given()
                .spec(RequestBuilder.getAuthorBooksSpec(request))
                .when()
                .get();
    }

    public static Response ResponsePostAuthor(RequestPostNewAuthor request) {
        return given()
                .spec(RequestBuilder.postAuthorSpec(request))
                .when()
                .post();
    }

    public static Response ResponsePostBook(RequestPostNewBook request) {
        return given()
                .spec(RequestBuilder.postAuthorBookSpec(request))
                .when()
                .post();
    }
}