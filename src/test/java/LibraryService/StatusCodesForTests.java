package LibraryService;

public class StatusCodesForTests {
    public static final int STATUS_CODE_FOR_SUCCESS_GET = 200;
    public static final int STATUS_CODE_FOR_INCORRECT_GET = 400;
    public static final int STATUS_CODE_FOR_NULL_GET = 400;
    public static final String ERROR_CODE_FOR_INCORRECT_GET = "1004";
    public static final String ERROR_MESSAGE_FOR_INCORRECT_GET = "Указанный автор не существует в таблице";
    public static final String ERROR_CODE_FOR_NULL_GET = "1001";
    public static final String ERROR_MESSAGE_FOR_NULL_GET = "Не передан id автора";

    public static final int STATUS_CODE_FOR_SUCCESS_POST = 201;
    public static final int STATUS_CODE_FOR_NULL_POST = 400;
    public static final int STATUS_CODE_FOR_INCORRECT_POST = 409;
    public static final String ERROR_CODE_FOR_INCORRECT_POST = "1004";
    public static final String ERROR_CODE_FOR_NULL_POST = "1001";
    public static final String ERROR_MESSAGE_FOR_INCORRECT_POST = "Указанный автор не существует в таблице";
    public static final String ERROR_MESSAGE_FOR_NULL_AUTHOR_POST = "Не передан обязательный параметр: author";
    public static final String ERROR_MESSAGE_FOR_NULL_TITLE_POST = "Не передан обязательный параметр: bookTitle";
    public static final String ERROR_DETAILS_FOR_NULL_POST = "Валидация не пройдена";
}
