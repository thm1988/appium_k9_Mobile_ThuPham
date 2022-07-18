package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;
import java.util.List;

public class SwipeVertically {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Login screen
            MobileElement navFormScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormScreenBtnElem.click();

            // Wait until screen is loaded
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Form Component\")")));



            // Fill in the input field
            MobileElement inputFieldElem = appiumDriver.findElement(MobileBy.AccessibilityId("text-input"));
            inputFieldElem.sendKeys("Test");

            // Switch on/off switcher
            MobileElement switchField = appiumDriver.findElement(MobileBy.AccessibilityId("switch"));
            switchField.click();

            // Dropdown field
//            MobileElement dropdownField = appiumDriver.findElement(MobileBy.AccessibilityId("Dropdown"));
//            dropdownField.click();
//            List<MobileElement> dropDownMenu =
//                    appiumDriver.findElements(MobileBy.className("android.widget.ListView"));
//            for (MobileElement downMenu : dropDownMenu) {
//                String textDropdown = downMenu.getText();
//                if (textDropdown.equalsIgnoreCase("Appium is awesome")) {
//                    downMenu.click();
//                    break;
//                }
//            }

            // Get the screen dimension
            Dimension screenDimension = appiumDriver.manage().window().getSize();
            int screenHeight = screenDimension.getHeight();
            int screenWidth = screenDimension.getWidth();

            // Calculate the start point/end point
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;

            int yStartPoint = 50 * screenHeight / 100;
            int yEndPoint = 10 * screenHeight / 100;

            // Point
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            // Simulate touch and drag - down
            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction.
                    press(startPoint).
                    waitAction(new WaitOptions().withDuration(Duration.ofMillis(500))).
                    moveTo(endPoint).
                    release().
                    perform();

            // Simulate touch and drag - Up
            TouchAction touchActionUp = new TouchAction(appiumDriver);
            touchActionUp.
//                    press(endPoint).
//                    waitAction(new WaitOptions().withDuration(Duration.ofMillis(500))).
                    longPress(endPoint).
                    moveTo(startPoint).
                    release().
                    perform();


            // Click on button activate
            MobileElement btnActive = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
            btnActive.click();

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(appiumDriver != null) appiumDriver.quit();
    }
}
