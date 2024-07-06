package preparingSteps.requests;

import model.requests.RequestGetAuthorBooksXML;
import model.requests.RequestGetAuthorsBooks;
import model.requests.RequestPostNewAuthor;
import model.requests.RequestPostNewBook;
import io.restassured.response.Response;
import model.responses.ResponseGetAuthorBooksXML;
import model.responses.ResponseGetAuthorsBooks;
import model.responses.ResponsePostNewAuthor;
import model.responses.ResponsePostNewBook;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestSender {
    public static ResponseGetAuthorBooksXML responseGetBooksXml(RequestGetAuthorBooksXML request) {
        Response response = given()
                .spec(RequestBuilder.getAuthorBooksXmlSpec(request))
                .when()
                .post();
        ResponseGetAuthorBooksXML responseGetAuthorBooksXML = response
                .as(ResponseGetAuthorBooksXML.class);
        responseGetAuthorBooksXML.setStatusCode(response.getStatusCode());

        return responseGetAuthorBooksXML;
    }

    public static List<ResponseGetAuthorsBooks> responseGetBooks(RequestGetAuthorsBooks request) {
        Response response = given()
                .spec(RequestBuilder.getAuthorBooksSpec(request))
                .when()
                .get();
        List<ResponseGetAuthorsBooks> responseGetAuthorsBooks = response
                .jsonPath()
                .getList(".", ResponseGetAuthorsBooks.class);
        responseGetAuthorsBooks.getFirst().setStatusCode(response.getStatusCode());

        return responseGetAuthorsBooks;
    }

    public static ResponsePostNewAuthor responsePostAuthor(RequestPostNewAuthor request) {
        Response response = given()
                .spec(RequestBuilder.postAuthorSpec(request))
                .when()
                .post();
        ResponsePostNewAuthor responsePostNewAuthor = response
                .as(ResponsePostNewAuthor.class);
        responsePostNewAuthor.setStatusCode(response.getStatusCode());

        return responsePostNewAuthor;
    }

    public static ResponsePostNewBook responsePostBook(RequestPostNewBook request) {
        Response response = given()
                .spec(RequestBuilder.postAuthorBookSpec(request))
                .when()
                .post();
        ResponsePostNewBook responsePostNewBook = response
                .as(ResponsePostNewBook.class);
        responsePostNewBook.setStatusCode(response.getStatusCode());

        return responsePostNewBook;
    }
}