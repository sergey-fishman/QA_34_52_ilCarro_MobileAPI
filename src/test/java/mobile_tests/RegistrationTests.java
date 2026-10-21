package mobile_tests;

import dto.User;
import org.testng.annotations.Test;
import screens.SearchScreen;
import screens.SplashScreen;
import static utils.UserFactory.*;

public class RegistrationTests extends TestBase {

    @Test
    public void registrationPositiveTest() {
        new SplashScreen(driver).goToSearchScreen();
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnRegistration();
        User user = positiveUser();
    }
}
