package models.components.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class LoginFormComponent {
    private final AppiumDriver<MobileElement> appiumDriver;

    private final static By usernameSel = MobileBy.AccessibilityId("input-email");

    private final static By incorrectEmailTxtSel = MobileBy.xpath("//*[contains(@text, 'Please enter a valid email address')]");
    private final static By passwordSel = MobileBy.AccessibilityId("input-password");

    private final static By incorrectPasswordTxtSel = MobileBy.xpath("//*[contains(@text, 'Please enter at least 8 characters')]");
    private final static By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    private final static By alertLoginSuccessfullySel = MobileBy.id("android:id/alertTitle");

    public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }
    public void inputUsername(String usernameTxt) {
        MobileElement userNameElem = appiumDriver.findElement(usernameSel);
        userNameElem.clear();
        if (!usernameTxt.isEmpty()) userNameElem.sendKeys(usernameTxt);
    }

    public String getInvalidEmailStr(){
        return appiumDriver.findElement(incorrectEmailTxtSel).getText();
    }
    public void inputPassword(String passwordTxt) {
        MobileElement passwordElem = appiumDriver.findElement(passwordSel);
        if (!passwordTxt.isEmpty()) passwordElem.sendKeys(passwordTxt);
    }

    public String getInvalidPasswordStr(){
        return appiumDriver.findElement(incorrectPasswordTxtSel).getText();
    }

    public void clickOnLoginBtn() {
        MobileElement loginBtnElem = appiumDriver.findElement(loginBtnSel);
        loginBtnElem.click();
    }

    public String getValidLoginSuccessfullyMsg(){
        return appiumDriver.findElement(alertLoginSuccessfullySel).getText();
    }
}
