package testflows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreen;
import org.apache.commons.validator.routines.EmailValidator;
import org.testng.Assert;
import testflows.BaseFlow;

public class LoginFlow extends BaseFlow {
    private String username;
    private String password;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver, String username, String password) {
        super(appiumDriver);
        this.username = username;
        this.password = password;
    }

    public void login() {
        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComp = loginScreen.loginFormComp();

        if(!username.isEmpty()) loginFormComp.inputUsername(username);

        if(!password.isEmpty()) loginFormComp.inputPassword(password);

        loginFormComp.clickOnLoginBtn();

    }
    public void verifyLogin(){
        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComp = loginScreen.loginFormComp();

        boolean isEmailValid = EmailValidator.getInstance().isValid(username);
        boolean isPasswordValid = password.length() >=8;

        if(isEmailValid && isPasswordValid){
            verifyCorrectLoginCreds(loginFormComp);
        }

        if(!isEmailValid){
            verifyIncorrectEmail(loginFormComp);
        }

        if(!isPasswordValid){
            verifyIncorrectPassword(loginFormComp);
        }
    }

    //TODO
    private void verifyCorrectLoginCreds(LoginFormComponent loginFormComp) {
        String actualValidLoginSuccessfullyMsg = loginFormComp.getValidLoginSuccessfullyMsg();
        String expectedValidLoginSuccessfullyMsg = "Success";
        Assert.assertEquals(actualValidLoginSuccessfullyMsg, expectedValidLoginSuccessfullyMsg, "[ERR] Invalid correct cred str is not correct");

    }

    private void verifyIncorrectEmail(LoginFormComponent loginFormComp) {
        String actualInvalidEmailStr = loginFormComp.getInvalidEmailStr();
        String expectedInvalidEmailStr = "Please enter a valid email address";

//        System.out.println("actualInvalidEmailStr: " + actualInvalidEmailStr);
//        System.out.println("expectedInvalidEmailStr: " + expectedInvalidEmailStr);
        Assert.assertEquals(actualInvalidEmailStr, expectedInvalidEmailStr, "[ERR] Invalid email str is not correct");
    }

    private void verifyIncorrectPassword(LoginFormComponent loginFormComp) {
        String actualInvalidPasswordStr = loginFormComp.getInvalidPasswordStr();
        String expectedInvalidPasswordStr = "Please enter at least 8 characters";

//        System.out.println("actualInvalidPasswordStr: " + actualInvalidPasswordStr);
//        System.out.println("expectedInvalidPasswordStr: " + expectedInvalidPasswordStr);
        Assert.assertEquals(actualInvalidPasswordStr, expectedInvalidPasswordStr, "[ERR] Invalid password str is not correct");
    }


}