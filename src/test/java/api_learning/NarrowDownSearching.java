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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NarrowDownSearching {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Login screen
            MobileElement navFormScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormScreenBtnElem.click();

            // Get the screen dimension
            Dimension screenDimension = appiumDriver.manage().window().getSize();
            int screenHeight = screenDimension.getHeight();
            int screenWidth = screenDimension.getWidth();

            // Calculate the start point/end point
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;

            int yStartPoint = 0;
            int yEndPoint = 50 * screenHeight / 100;

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

            Map<String, String> notificationContent = new HashMap<>();
            List<MobileElement> notiticationsList = appiumDriver.findElements(MobileBy.id("com.android.systemui:id/expanded"));
            for (MobileElement mobileElement : notiticationsList) {
                MobileElement titleElem = appiumDriver.findElement(MobileBy.id("android:id/title"));
                MobileElement contentElem = appiumDriver.findElement(MobileBy.id("android:id/text"));
                notificationContent.put(titleElem.getText().trim(),contentElem.getText().trim());
            }
            if(notificationContent.keySet().isEmpty()) {
                throw new RuntimeException("No Notifications");
            }

            for (String s : notificationContent.keySet()) {
                System.out.println(s);
                System.out.println(notificationContent.get(s));
            }
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(appiumDriver != null) appiumDriver.quit();
    }
}
