package mobile_tests;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import static utils.UserFactory.*;

public class RegistrationTests extends TestBase {
    SearchScreen searchScreen;
    RegistrationScreen registrationScreen;
    User user;

    @BeforeMethod
    public void setRegistrationScreen() {
        new SplashScreen(driver).waitForSearchScreen();
        searchScreen = new SearchScreen(driver);
        searchScreen.clickActionMenu();
        searchScreen.clickLinkRegistration();
        registrationScreen = new RegistrationScreen(driver);
        user = positiveUser();
    }

    @Test
    public void registrationPositiveTest() {
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnRegistration();
        Assert.assertTrue(searchScreen.validateTextSuccessMessagePresent
                ("Registration success!"));
    }

    @Test
    public void registrationEmptyNameNegativeTest() {
        user.setFirstName("");
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInErrorMessage("All fields must be filled and agree terms"));
    }
}
