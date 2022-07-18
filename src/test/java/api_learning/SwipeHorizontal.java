package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;

public class SwipeHorizontal {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Login screen
            MobileElement navFormScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
            navFormScreenBtnElem.click();

            // Wait until screen is loaded
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Swipe horizontal\")")));

            // Get the screen dimension
            Dimension screenDimension = appiumDriver.manage().window().getSize();
            int screenHeight = screenDimension.getHeight();
            int screenWidth = screenDimension.getWidth();

            // Calculate the start point/end point
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 10 * screenWidth / 100;

            int yStartPoint = 70 * screenHeight / 100;
            int yEndPoint = 70 * screenHeight / 100;

            // Point
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            // Simulate touch and drag - right to left
            TouchAction touchActionRightToLeft = new TouchAction(appiumDriver);
            touchActionRightToLeft.
                    press(startPoint).
                    waitAction(new WaitOptions().withDuration(Duration.ofMillis(500))).
                    moveTo(endPoint).
                    release().
                    perform();

            // Simulate touch and drag - left to right
            TouchAction touchActionUpLeftToRight = new TouchAction(appiumDriver);
            touchActionUpLeftToRight.
//                    press(endPoint).
//                    waitAction(new WaitOptions().withDuration(Duration.ofMillis(500))).
                    longPress(endPoint).
                    moveTo(startPoint).
                    release().
                    perform();

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(appiumDriver != null) appiumDriver.quit();
    }
}
