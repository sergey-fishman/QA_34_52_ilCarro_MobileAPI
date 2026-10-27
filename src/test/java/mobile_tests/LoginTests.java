package mobile_tests;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import utils.UserFactory;

import static utils.PropertiesReader.*;

public class LoginTests extends TestBase {
    SearchScreen searchScreen;
    LoginScreen loginScreen;
    User user;

    @BeforeMethod
    public void setLoginScreen() {
        new SplashScreen(driver).waitForSearchScreen();
        searchScreen = new SearchScreen(driver);
        searchScreen.clickActionMenu();
        searchScreen.clickLinkLogin();
        loginScreen = new LoginScreen(driver);
    }

    @Test
    public void loginPositiveTest() {
        user = User.builder()
                .username(getProperty("base.properties", "username"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnLogin();
        Assert.assertTrue(searchScreen.validateTextLoginSuccessMessagePresent
                ("Login success!"), "Login success!");
    }

    @Test
    public void loginIgnoreFieldsNegativeTest() {
        loginScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver).validateTextInErrorMessage
                        ("All fields must be filled and agree terms"),
                "All fields must be filled and agree terms");
    }

    @Test
    public void loginEmptyUsernameNegativeTest() {
        user = User.builder()
                .username("")
                .password(getProperty("base.properties", "password"))
                .build();
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver).validateTextInErrorMessage
                        ("All fields must be filled and agree terms"),
                "All fields must be filled and agree terms");
    }

    @Test
    public void loginEmptyPasswordNegativeTest() {
        user = User.builder()
                .username(getProperty("base.properties", "username"))
                .password("")
                .build();
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver).validateTextInErrorMessage
                        ("All fields must be filled and agree terms"),
                "All fields must be filled and agree terms");
    }

    @Test
    public void loginWrongUsernameNegativeTest() {
        user = User.builder()
                .username(UserFactory.positiveUser().getUsername())
                .password(getProperty("base.properties", "password"))
                .build();
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver).validateTextInErrorMessage
                        ("Login or Password incorrect"),
                "Login or Password incorrect");
    }

    @Test
    public void loginWrongPasswordNegativeTest() {
        user = User.builder()
                .username(getProperty("base.properties", "username"))
                .password(UserFactory.positiveUser().getPassword())
                .build();
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver).validateTextInErrorMessage
                        ("Login or Password incorrect"),
                "Login or Password incorrect");
    }
}
