package controllers;

import dto.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import utils.BaseApi;
import utils.PropertiesReader;

import java.awt.print.Book;

import static io.restassured.RestAssured.given;

public class CarController implements BaseApi {

    public TokenDto tokenDto;

    @BeforeClass
    public void login() {
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "username"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        tokenDto = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + LOGIN_URL)
                .thenReturn()
                .getBody().as(TokenDto.class);
    }

    public Response addNewCar(Car car) {
        return given()
                .log().body()
                .body(car)
                .contentType(ContentType.JSON)
                .header(AUTH, tokenDto.getAccessToken())
                .when()
                .post(BASE_URL + CARS_URL)
                .then()
                .log().status()
                .log().body()
                .extract()
                .response();
    }

    public Response getUserCars() {
        return given()
                .header(AUTH, tokenDto.getAccessToken())
                .when()
                .get(BASE_URL + CARS_MY_URL)
                .then()
                .log().status()
                .log().body()
                .extract()
                .response();
    }

    public Response bookCarBySerialNumber(String serialNumber, BookingPeriodDto period) {
        return given()
                .log().all()
                .body(period)
                .contentType(ContentType.JSON)
                .header(AUTH, tokenDto.getAccessToken())
                .when()
                .post(BASE_URL + CARS_URL + "/" + serialNumber + "/booking")
                .then()
                .log().status()
                .log().body()
                .extract()
                .response();
    }

    public Response deleteCarBySerialNumber(String serialNumber) {
        return given()
                .log().all()
                .header(AUTH, tokenDto.getAccessToken())
                .when()
                .delete(BASE_URL+CARS_URL+"/"+serialNumber)
                .then()
                .log().status()
                .log().body()
                .extract()
                .response();
    }

    public Response searchCarByCityPeriod(SearchDto searchDto) {
        return given()
                .log().all()
                .body(searchDto)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+CARS_SEARCH_URL)
                .then()
                .log().status()
                .log().body()
                .extract()
                .response();
    }
}
