package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id="inputUsername")
    private WebElement inputUserName;

    @FindBy(id="inputPassword")
    private WebElement inputPassword;

    @FindBy(id="signupUrl")
    private WebElement signupUrl;

    @FindBy(id="loginButton")
    private WebElement loginButton;

    @FindBy(id="errorAlert")
    private WebElement errorAlert;

    @FindBy(id="logoutSuccessAlert")
    private WebElement logoutSuccessAlert;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public String getUsername() {
        return inputUserName.getAttribute("value");
    }
    public String getPassword() {
        return inputPassword.getAttribute("value");
    }
    public  void submitLogin(){
        loginButton.click();
    }

    public void insertLoginCredentials(String username,String password){
        inputUserName.click();
        inputUserName.clear();
        inputUserName.sendKeys(username);
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys(password);

    }
    public void clickSignup(){
        signupUrl.click();
    }
    public String getErrorMessage(){
        return errorAlert.getText();
    }

    public String getLogoutSuccessMessage(){
        return logoutSuccessAlert.getText();
    }

}
