package mobile_tests;

import data_providers.UserDataProvider;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import utils.PropertiesReader;
import utils.TestNGListener;
import static utils.UserFactory.*;
@Listeners(TestNGListener.class)

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
                ("Registration success!"), "Registration success!");
    }

    @Test(dataProvider = "dataProviderPositiveCombinations", dataProviderClass = UserDataProvider.class)
    public void registrationDataDrivenPositiveTest(User user) {
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnRegistration();
        Assert.assertTrue(searchScreen.validateTextSuccessMessagePresent
                ("Registration success!"),"Registration success!");
    }

    @Test
    public void registrationExistingUserNegativeTest() {
        user.setUsername(PropertiesReader.getProperty("base.properties", "username"));
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver).validateTextInErrorMessage
                        ("User already exists"), "User already exists");
    }


    @Test
    public void registrationEmptyNameNegativeTest() {
        user.setFirstName("");
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnRegistration();
        System.out.println(user);
        Assert.assertTrue(new ErrorScreen(driver).validateTextInErrorMessage
                        ("All fields must be filled and agree terms"),
                "All fields must be filled and agree terms");
    }

    @Test
    public void registrationEmptyLastNameNegativeTest() {
        user.setLastName("");
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnRegistration();
        System.out.println(user);
        Assert.assertTrue(new ErrorScreen(driver).validateTextInErrorMessage
                        ("All fields must be filled and agree terms"),
                "All fields must be filled and agree terms");
    }

    @Test
    public void registrationEmptyEmailNegativeTest() {
        user.setUsername("");
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnRegistration();
        System.out.println(user);
        Assert.assertTrue(new ErrorScreen(driver).validateTextInErrorMessage
                        ("All fields must be filled and agree terms"),
                "All fields must be filled and agree terms");
    }

    @Test
    public void registrationEmptyPasswordNegativeTest() {
        user.setPassword("");
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnRegistration();
        System.out.println(user);
        Assert.assertTrue(new ErrorScreen(driver).validateTextInErrorMessage
                        ("All fields must be filled and agree terms"),
                "All fields must be filled and agree terms");
    }

    @Test
    public void registrationUnsignedCheckboxNegativeTest() {
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickBtnRegistration();
        System.out.println(user);
        Assert.assertTrue(new ErrorScreen(driver).validateTextInErrorMessage
                        ("All fields must be filled and agree terms"),
                "All fields must be filled and agree terms");
    }

    @Test(dataProvider = "dataProviderWrongUsername", dataProviderClass = UserDataProvider.class)
    public void registrationWrongEmailWithDataProviderNegativeTest(User user) {
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnRegistration();
        System.out.println(user);
        Assert.assertTrue(new ErrorScreen(driver).validateTextInErrorMessage
                        ("{username=must be a well-formed email address}"),
                "{username=must be a well-formed email address}");
    }

    @Test(dataProvider = "dataProviderWrongPassword", dataProviderClass = UserDataProvider.class)
    public void registrationWrongPasswordWithDataProviderNegativeTest(User user) {
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnRegistration();
        System.out.println(user);
        Assert.assertTrue(new ErrorScreen(driver).validateTextInErrorMessage
                        ("{password= At least 8 characters;"),
                "{password= At least 8 characters;");
    }
}
