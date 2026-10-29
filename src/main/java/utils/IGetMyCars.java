package utils;

import dto.Cars;
import dto.TokenDto;
import dto.User;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public interface IGetMyCars extends BaseApi {
    default int getCarsCount() {
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "username"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();

        TokenDto tokenDto = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + LOGIN_URL)
                .thenReturn()
                .getBody().as(TokenDto.class);

        Cars cars = given()
                .contentType(ContentType.JSON)
                .header(AUTH, tokenDto.getAccessToken())
                .when()
                .get(BASE_URL + CARS_MY_URL)
                .then()
                .statusCode(200)
                .extract().as(Cars.class);

        return cars.getCars().length;
    }
}
