package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import platform.Platform;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory implements MobileCapabilityTypeEx {

    public static AppiumDriver<MobileElement> getDriver(Platform platform) {
        AppiumDriver<MobileElement> appiumDriver = null;

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(UDID, "0A221FDD4000L2");
        desiredCapabilities.setCapability(APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (appiumServer == null) {
            throw new RuntimeException("[ERR] URL is null");
        }

        switch (platform) {
            case ANDROID:
                appiumDriver = new AndroidDriver<>(appiumServer, desiredCapabilities);
                break;
            case IOS:
                appiumDriver = new IOSDriver<>(appiumServer, desiredCapabilities);
                break;
        }

        // Implicit wait
        appiumDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return appiumDriver;
    }

}