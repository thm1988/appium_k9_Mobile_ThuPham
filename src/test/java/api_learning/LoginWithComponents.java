package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreen;
import models.pages.LoginScreenMod03;
import platform.Platform;

public class LoginWithComponents {
    public static void main(String[] args) throws InterruptedException {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            LoginScreen loginScreen = new LoginScreen(appiumDriver);
            BottomNavComponent bottomNavComp = loginScreen.bottomNavComp();
            LoginFormComponent loginFormComp = loginScreen.loginFormComp();

            bottomNavComp.clickOnLoginIcon();
            loginFormComp.inputUsername("teo@sth.com");
            loginFormComp.inputPassword("12345678");
            loginFormComp.clickOnLoginBtn();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(5000);
        if (appiumDriver!= null) appiumDriver.quit();
    }
}
