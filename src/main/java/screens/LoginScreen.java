package screens;

import dto.User;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginScreen extends BaseScreen{
    public LoginScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.telran.ilcarro:id/editLoginEmail")
    WebElement inputLoginEmail;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editLoginPassword")
    WebElement inputLoginPassword;
    @AndroidFindBy(id = "com.telran.ilcarro:id/loginBtn")
    WebElement loginBtn;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text=\"Login success!\"]")
    WebElement successMessage;

    public void typeLoginForm(User user){
        inputLoginEmail.sendKeys(user.getUsername());
        inputLoginPassword.sendKeys(user.getPassword());
    }

    public void clickBtnLogin() {
        loginBtn.click();
    }

    public boolean validateTextLoginSuccessMessagePresent(String text) {
        return isTextInElementPresent(successMessage, text);
    }
}
