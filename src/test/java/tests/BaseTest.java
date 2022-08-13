package tests;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import platform.Platform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseTest {
    protected static AppiumDriver<MobileElement> appiumDriver;

    private static final List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    protected AppiumDriver<MobileElement> getDriver(){
        return driverThread.get().getDriver(Platform.ANDROID, udid, systemPort);
    }
    @BeforeTest
    @Parameters({"udid","systemPort"})
    public void initAppiumSession (String udid, String systemPort) {
        appiumDriver = DriverFactory.getDriver(Platform.ANDROID, udid, systemPort);
    }

    @AfterTest(alwaysRun = true)
    public void quitAppiumSession () {
        appiumDriver.quit();
    }
}