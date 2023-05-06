package api_models;

import config.ApiConfig;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static config.ApiConfig.*;
import static io.restassured.RestAssured.given;

public class OrderClient {

    public OrderClient() {
        RestAssured.baseURI = ApiConfig.BASE_URL;
    }

    @Step("Создание заказа")
    public ValidatableResponse create(Order order, String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .spec(requestSpec())
                .and()
                .body(order.getIngredientsMap())
                .when()
                .post(ORDER_CREATE)
                .then();
    }


    @Step("Получения информации об ингредиентах")
    public ValidatableResponse getIngredients() {
        return given()
                .spec(requestSpec())
                .when()
                .get(ORDER_GET_INGREDIENTS)
                .then();
    }

    @Step("Получение конкретного заказа")
    public ValidatableResponse getUserOrder(User user, String accessToken) { //доработать
        return given()
                .header("Authorization", accessToken)
                .spec(requestSpec())
                .and()
                .body(user)
                .when()
                .get(ORDER_GET)
                .then();
    }

}
