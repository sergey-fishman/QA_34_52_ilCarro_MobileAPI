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
    @AndroidFindBy(id = "com.telran.ilcarro:id/findTitle")
    WebElement title;

    public void clickActionMenu(){
        clickWait(actionMenu);
    }

    public void clickLinkLogin(){
        clickWait(linkLogin);
    }

    public void clickLinkRegistration(){
        clickWait(linkRegistration);
    }

    public boolean validateTextInTitlePresent(String text) {
        return isTextInElementPresent(title, text);
    }
}
