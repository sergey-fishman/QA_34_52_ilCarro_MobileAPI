package mobile_tests;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import static utils.UserFactory.*;

public class RegistrationTests extends TestBase {

    @Test
    public void registrationPositiveTest() {
        new SplashScreen(driver).waitForSearchScreen();

        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickActionMenu();
        searchScreen.clickLinkRegistration();

        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        User user = positiveUser();
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnRegistration();

        Assert.assertTrue(searchScreen.validateTextInTitlePresent
                ("Find your car now!"), "Find your car now! is present");
    }
}
