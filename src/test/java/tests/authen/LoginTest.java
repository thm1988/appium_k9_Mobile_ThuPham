package tests.authen;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import platform.Platform;
import testflows.authentication.LoginFlow;

public class LoginTest {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            LoginFlow loginFlow = new LoginFlow(appiumDriver,
                    "teo@something.com,","12345678");
            loginFlow.login();
        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
