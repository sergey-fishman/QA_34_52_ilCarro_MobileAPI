package api_rest_tests;

import controllers.AuthController;
import data_providers.UserDataProvider;
import dto.TokenDto;
import dto.User;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
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
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(dataProvider = "dataProviderPositiveCombinations", dataProviderClass = UserDataProvider.class)
    public void registrationPositiveCombinationsTest(User user){
        Response response = registrationLogin(user, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void loginPositiveTest(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "username"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        Response response = registrationLogin(user, LOGIN_URL);
        TokenDto tokenDto = response.getBody().as(TokenDto.class);
        System.out.println(tokenDto.getAccessToken());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void loginEmptyUsernameNegativeTest(){
        User user = User.builder()
                .username("")
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        Response response = registrationLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void loginEmptyPasswordNegativeTest(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "username"))
                .password("")
                .build();
        Response response = registrationLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void loginUnregisteredUsernameNegativeTest(){
        User user = positiveUser();
        user.setPassword(PropertiesReader.getProperty("base.properties", "password"));
        Response response = registrationLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void loginIncorrectPasswordNegativeTest(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "username"))
                .password("Qwerrrty1542&")
                .build();
        Response response = registrationLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void registrationExistingUserNegativeTest(){
        User user = positiveUser();
        user.setUsername(PropertiesReader.getProperty("base.properties", "username"));
        user.setPassword(PropertiesReader.getProperty("base.properties", "password"));
        Response response = registrationLogin(user, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void registrationEmptyUsernameNegativeTest(){
        User user = positiveUser();
        user.setUsername("");
        Response response = registrationLogin(user, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    public void registrationNullUsernameNegativeTest(){
        User user = positiveUser();
        user.setUsername(null);
        Response response = registrationLogin(user, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    public void registrationEmptyPasswordNegativeTest(){
        User user = positiveUser();
        user.setPassword("");
        Response response = registrationLogin(user, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    public void registrationNullPasswordNegativeTest(){
        User user = positiveUser();
        user.setPassword(null);
        Response response = registrationLogin(user, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test(dataProvider = "dataProviderWrongUsername", dataProviderClass = UserDataProvider.class)
    public void registrationWrongUsernameFormatTest(User user){
        Response response = registrationLogin(user, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test(dataProvider = "dataProviderWrongPassword", dataProviderClass = UserDataProvider.class)
    public void registrationWrongPasswordFormatTest(User user){
        Response response = registrationLogin(user, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    public void registrationSQLInjectionNegativeTest(){
        User user = positiveUser();
        user.setUsername("user@example.com'; DROP TABLE users;--");
        user.setPassword("Ab1@$#^&' OR '1'='1");
        Response response = registrationLogin(user, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    public void registrationWrongMethodNegativeTest(){
        User user = positiveUser();
        Response response = registrationLoginWithMethodParameter(user, REGISTRATION_URL, Method.GET);
        Assert.assertEquals(response.getStatusCode(), 403);
    }

    @Test
    public void registrationUnsupportedMethodNegativeTest(){
        User user = positiveUser();
        Response response = registrationLoginWithMethodParameter(user, REGISTRATION_URL, Method.PUT);
        Assert.assertEquals(response.getStatusCode(), 403);
    }

    @Test
    public void registrationWrongContentTypeNegativeTest(){
        User user = positiveUser();
        Response response = registrationLoginWithContentParameter(user, REGISTRATION_URL, ContentType.XML);
        Assert.assertEquals(response.getStatusCode(), 500);
    }

    @Test
    public void loginQLInjectionUsernameNegativeTest(){
        User user = positiveUser();
        user.setUsername("admin' OR '1'='1");
        user.setPassword("");
        Response response = registrationLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void loginUnsupportedMethodNegativeTest(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "username"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        Response response = registrationLoginWithMethodParameter(user, LOGIN_URL, Method.PUT);
        Assert.assertEquals(response.getStatusCode(), 403);
    }

    @Test
    public void loginWrongContentTypeNegativeTest(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "username"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        Response response = registrationLoginWithContentParameter(user, LOGIN_URL, ContentType.XML);
        Assert.assertEquals(response.getStatusCode(), 500);
    }

    @Test
    public void loginArrayBodyNegativeTest(){
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "username"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        User[] userArray = {user};
        Response response = registrationLoginWithUserArray(userArray, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    public void registrationArrayBodyNegativeTest(){
        User user = positiveUser();
        User[] userArray = {user};
        Response response = registrationLoginWithUserArray(userArray, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 500);
    }

    @Test
    public void registrationInvalidJsonSyntaxNegativeTest(){
        String rawJson = "{\"username\": \"karry.beatty@yahoo.com\"," +
                "\"password\": \"Qwerty1234!\"," +
                "\"firstName\": \"Ginger\"," +
                "\"lastName\": \"Kuhlman\"";
        Response response = registrationLoginWithRawJson(rawJson, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 500);
    }
}
