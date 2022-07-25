package api_learning;

import driver.AppPackages;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import platform.Platform;

import java.time.Duration;

public class HandleMultipleApps {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try{

            // Navigate to Login screen
            MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtnElem.click();

            MobileElement emailInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement passwordInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

            emailInputElem.sendKeys("test12345@gmail.com");
            passwordInputElem.sendKeys("123456789");
            loginBtnElem.click();

            // Put app into background
//            appiumDriver.runAppInBackground(Duration.ofSeconds(3));

            // Put App into background until call it back
            appiumDriver.runAppInBackground(Duration.ofSeconds(-1));

            // Switch into another app | Go to Settings toggle wifi
            appiumDriver.activateApp(AppPackages.SETTINGS);

            // Navigate to network list

            MobileElement networkLabelSel =
                    appiumDriver
                            .findElement(MobileBy.AndroidUIAutomator
                                    ("new UiSelector().textContains(\"Network & internet\")"));

            networkLabelSel.click();

            // Navigate to Internet
            MobileElement internetLabelSel =
                    appiumDriver
                            .findElement(MobileBy.AndroidUIAutomator
                                    ("new UiSelector().textContains(\"Internet\")"));

            internetLabelSel.click();

            // Toggle ON/OFF
            By wifiToggleSel = MobileBy.id("android:id/switch_widget");
            MobileElement wifiToggleElem = appiumDriver.findElement(wifiToggleSel);
            if (wifiToggleElem.isSelected()) wifiToggleElem.click();

            // Come back to the app > interact with other elements
            appiumDriver.activateApp(AppPackages.WEBDRIVER_IO);
            appiumDriver.findElement(MobileBy.xpath("//*[@text='OK']")).click();


        } catch (Exception e) {
            e.printStackTrace();
        }

        if (appiumDriver!= null) appiumDriver.quit();
    }
}
