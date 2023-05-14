package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiConfig {

    public final static String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    public final static String USER_REGISTER = "api/auth/register";
    public final static String USER_LOGIN = "api/auth/login";
    public final static String USER_GET_UPDATE_DELETE = "api/auth/user";

    public final static String ORDER_GET_INGREDIENTS = "api/ingredients";
    public final static String ORDER_CREATE = "api/orders";
    public final static String ORDER_GET = "api/orders";

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build()
                .log()
                .all();
    }

}
