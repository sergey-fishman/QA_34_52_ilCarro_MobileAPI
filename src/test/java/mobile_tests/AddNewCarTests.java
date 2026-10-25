package mobile_tests;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;
import utils.CarFactory;

import static utils.PropertiesReader.getProperty;

public class AddNewCarTests extends TestBase {
    SearchScreen searchScreen;
    LoginScreen loginScreen;
    AddNewCarScreen addNewCarScreen;

    @BeforeMethod
    public void setLoginScreen() {
        new SplashScreen(driver).waitForSearchScreen();
        searchScreen = new SearchScreen(driver);
        searchScreen.clickActionMenu();
        searchScreen.clickLinkLogin();
        loginScreen = new LoginScreen(driver);
        User user = User.builder()
                .username(getProperty("base.properties", "username"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnLogin();
        searchScreen = new SearchScreen(driver);
        searchScreen.clickActionMenu();
        searchScreen.clickLinkMyCars();
        new MyCarsScreen(driver).clickAddCarBtn();
        addNewCarScreen = new AddNewCarScreen(driver);
    }

    @Test
    public void addCarPositiveTest(){
        addNewCarScreen.typeAddCarForm(CarFactory.positiveCar());
        addNewCarScreen.choseFuelType("Diesel");
        addNewCarScreen.clickAddCarBtn();
        Assert.assertTrue(addNewCarScreen.validateSuccessMessage
                ("Car was added!"));
    }
}
