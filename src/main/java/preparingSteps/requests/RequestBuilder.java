package preparingSteps.requests;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import model.requests.RequestGetAuthorBooksXML;
import model.requests.RequestGetAuthorsBooks;
import model.requests.RequestPostNewAuthor;
import model.requests.RequestPostNewBook;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class RequestBuilder {
    private static final String BASE_URI = "http://localhost:8080/library";

    public static RequestSpecBuilder createSpecBuilder() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter());
    }

    public static RequestSpecification postAuthorSpec(RequestPostNewAuthor request) {
        return createSpecBuilder()
                .setBasePath("authors/save")
                .setBody(request)
                .build();
    }

    public static RequestSpecification postAuthorBookSpec(RequestPostNewBook request) {
        return createSpecBuilder()
                .setBasePath("books/save")
                .setBody(request)
                .build();
    }

    public static RequestSpecification getAuthorBooksSpec(RequestGetAuthorsBooks request) {
        return createSpecBuilder()
                .setBasePath(String.format("authors/%s/books", request.getId()))
                .build();
    }

    public static RequestSpecification getAuthorBooksXmlSpec(RequestGetAuthorBooksXML request) {
        return createSpecBuilder()
                .setBasePath("authors/books")
                .setContentType(ContentType.XML)
                .setBody(request)
                .build();
    }
}