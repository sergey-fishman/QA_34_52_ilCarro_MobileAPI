package mobile_tests;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;
import utils.CarFactory;
import utils.IGetMyCars;

import java.util.List;

import static utils.PropertiesReader.getProperty;

public class DeleteCarTests extends TestBase implements IGetMyCars {
    SearchScreen searchScreen;
    LoginScreen loginScreen;
    AddNewCarScreen addNewCarScreen;
    MyCarsScreen myCarsScreen;

    @BeforeMethod
    public void setDeleteCarTest() {
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
        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.clickAddCarBtn();
        addNewCarScreen = new AddNewCarScreen(driver);
        addNewCarScreen.typeAddCarForm(CarFactory.positiveCar());
        addNewCarScreen.chooseFuelType("Diesel");
        addNewCarScreen.clickAddCarBtn();
    }

    @Test
    public void deleteCarPositiveTest() {
        List<String> listBeforeDelete = myCarsScreen.readCarNumberList();
        myCarsScreen.deleteCar();
        myCarsScreen.clickBtnYes();
        List<String> listAfterDelete = myCarsScreen.readCarNumberList();
        Assert.assertNotEquals(listBeforeDelete, listAfterDelete);
    }

    @Test
    public void deleteCarWithAPICheckPositiveTest() {
        int carsBeforeDelete = getCarsCount();
        System.out.println(carsBeforeDelete);
        myCarsScreen.deleteCar();
        myCarsScreen.clickBtnYes();
        int carsAfterDelete = getCarsCount();
        System.out.println(carsAfterDelete);
        Assert.assertNotEquals(carsBeforeDelete, carsAfterDelete);
    }
}