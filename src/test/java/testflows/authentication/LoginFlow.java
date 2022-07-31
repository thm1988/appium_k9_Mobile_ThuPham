package testflows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreen;

public class LoginFlow {
    private final AppiumDriver<MobileElement> appiumDriver;
    private String username;
    private String password;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver, String username, String password) {
        this.appiumDriver = appiumDriver;
        this.username = username;
        this.password = password;
    }

    public void login() {
        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComp = loginScreen.loginFormComp();

        if(!username.isEmpty()) loginFormComp.inputUsername(username);

        if(!password.isEmpty()) loginFormComp.inputUsername(password);

        loginFormComp.clickOnLoginBtn();

    }


}