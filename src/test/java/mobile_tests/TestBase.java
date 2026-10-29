package mobile_tests;

import config.AppiumConfig;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestBase {
    protected static AppiumDriver driver;
    public static int height = 0, width = 0;

    @BeforeMethod
    public void setup() {
        driver = AppiumConfig.createAppiumDriver();
        height = driver.manage().window().getSize().getHeight();
        width = driver.manage().window().getSize().getWidth();
    }

    @Test(enabled = false)
    public void start(){}

    @AfterMethod(enabled = false)
    public void teardown(){
        if (driver != null)
            driver.quit();
    }
}
