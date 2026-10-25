package mobile_tests;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;
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
        Assert.assertTrue(loginScreen.validateTextLoginSuccess
                ("Login success!"));
    }
}
