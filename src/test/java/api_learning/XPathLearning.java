package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.util.List;

public class XPathLearning {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Login screen
            MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtnElem.click();

            List<MobileElement> emailPassElem = appiumDriver.findElements(MobileBy.xpath("//android.widget.EditText"));
            final int INDEX_USERNAME = 0;
            final int INDEX_PASSWORD = 1;
            emailPassElem.get(INDEX_USERNAME).sendKeys("ptthu36@gmail.com");
            emailPassElem.get(INDEX_PASSWORD).sendKeys("12345");
//            emailPassElem.get(0).sendKeys("ptthu36@gmail.com");
//            emailPassElem.get(1).sendKeys("12345");

            // Find login info from screen
            MobileElement loginInstructionElem =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"When the device\")"));
            System.out.println(loginInstructionElem.getText());

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(appiumDriver != null) appiumDriver.quit();
    }
}
