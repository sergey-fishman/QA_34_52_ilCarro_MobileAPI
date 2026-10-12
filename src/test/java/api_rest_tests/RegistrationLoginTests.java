package api_rest_tests;

import controllers.AuthController;
import dto.TokenDto;
import dto.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertiesReader;

import static utils.UserFactory.*;

public class RegistrationLoginTests extends AuthController {

    @Test
    public void registrationPositiveTest(){
        User user = positiveUser();
        Response response = registrationLogin(user, REGISTRATION_URL);
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void loginPositiveTest(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "email"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        Response response = registrationLogin(user, LOGIN_URL);
        TokenDto tokenDto = response.getBody().as(TokenDto.class);
        System.out.println(tokenDto.getAccessToken());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
