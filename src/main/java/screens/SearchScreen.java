package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SearchScreen extends BaseScreen {
    public SearchScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    WebElement actionMenu;
    @AndroidFindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/title' and @text='Login']")
    WebElement linkLogin;
    @AndroidFindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/title' and @text='Registration']")
    WebElement linkRegistration;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text=\"Registration success!\"]")
    WebElement registrationSuccessMessage;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text=\"Login success!\"]")
    WebElement loginSuccessMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.telran.ilcarro:id/title\" and @text=\"Logout\"]")
    WebElement linkLogout;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.telran.ilcarro:id/title\" and @text=\"My Cars\"]")
    WebElement linkMyCars;

    public void clickActionMenu(){
        clickWait(actionMenu);
    }

    public void clickLinkLogin(){
        clickWait(linkLogin);
    }

    public void clickLinkLogout() {
        clickWait(linkLogout);
    }

    public void clickLinkMyCars(){
        clickWait(linkMyCars);
    }

    public void clickLinkRegistration(){
        clickWait(linkRegistration);
    }

    public boolean validateTextSuccessMessagePresent(String text) {
        return isTextInElementPresent(registrationSuccessMessage, text);
    }

    public boolean validateTextLoginSuccessMessagePresent(String text) {
        return isTextInElementPresent(loginSuccessMessage, text);
    }
}
