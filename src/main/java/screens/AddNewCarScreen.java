package screens;

import dto.Car;
import dto.Cars;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddNewCarScreen extends BaseScreen {
    public AddNewCarScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.telran.ilcarro:id/editSerial")
    WebElement inputSerial;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editMan")
    WebElement inputManufacture;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editModel")
    WebElement inputModel;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editCity")
    WebElement inputCity;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editPrice")
    WebElement inputPrice;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editCarClass")
    WebElement inputClass;
    @AndroidFindBy(id = "com.telran.ilcarro:id/text1")
    WebElement inputFuel;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editYear")
    WebElement inputYear;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editSeats")
    WebElement inputSeats;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editAbout")
    WebElement inputAbout;
    @AndroidFindBy(id = "com.telran.ilcarro:id/addCarBtn")
    WebElement addCarBtn;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text=\"Car was added!\"]")
    WebElement successMessage;

    public void typeAddCarForm(Car car) {
        inputSerial.sendKeys(car.getSerialNumber());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputCity.sendKeys(car.getCity());
        inputPrice.sendKeys(car.getPricePerDay().toString());
        inputClass.sendKeys(car.getCarClass());
        inputYear.sendKeys(car.getYear());
        inputSeats.sendKeys(car.getSeats().toString());
        inputAbout.sendKeys(car.getAbout());

    }

    public void chooseFuelType(String fuel) {
        inputFuel.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@text='" + fuel + "']"))).click();
    }

    public void clickAddCarBtn() {
        addCarBtn.click();
    }

    public boolean validateSuccessMessage(String text){
        return isTextInElementPresent(successMessage, text);
    }

}
