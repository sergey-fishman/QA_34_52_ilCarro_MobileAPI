package controllers;

import dto.User;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import utils.BaseApi;

import static io.restassured.RestAssured.given;

public class AuthController implements BaseApi {

    public Response registrationLogin(User user, String url) {
        return given()
                .log().body()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + url)
                .then()
                .log().status()
                .log().body()
                .extract()
                .response();
    }

    public Response registrationLoginWithUserArray(User[] userArray, String url) {
        return given()
                .log().body()
                .body(userArray)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + url)
                .then()
                .log().status()
                .log().body()
                .extract()
                .response();
    }

    public Response registrationLoginWithRawJson(String rawJson, String url) {
        return given()
                .log().body()
                .body(rawJson)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + url)
                .then()
                .log().status()
                .log().body()
                .extract()
                .response();
    }

    public Response registrationLoginWithMethodParameter(User user, String url, Method method) {
        return given()
                .log().method()
                .log().body()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .request(method, BASE_URL + url)
                .then()
                .log().status()
                .log().body()
                .extract()
                .response();
    }

    public Response registrationLoginWithContentParameter(User user, String url, ContentType contentType) {
        return given()
                .log().headers()
                .log().body()
                .body(user)
                .contentType(contentType)
                .when()
                .post(BASE_URL + url)
                .then()
                .log().status()
                .log().body()
                .extract()
                .response();
    }
}
