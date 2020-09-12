package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id="inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id="inputLastName")
    private WebElement inputLastName;

    @FindBy(id="inputUsernameSignup")
    private WebElement inputUsername;

    @FindBy(id="inputPasswordSignup")
    private WebElement inputPassword;

    @FindBy(id="signupSubmitButton")
    private WebElement signupSubmitButton;

    @FindBy(id="backToLoginUrl")
    private WebElement backToLoginUrl;

    @FindBy(id="signupSuccessAlert")
    private WebElement signupSuccessAlert;

    @FindBy(id="signupUserExistsAlert")
    private WebElement signupUserExistsAlert;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public String getFirstName (){
        return inputFirstName.getAttribute("value");
    }

    public String getLastName (){
        return inputLastName.getAttribute("value");
    }
    public String getPassword(){
        return inputPassword.getAttribute("value");
    }
    public String getUsername(){
        return inputUsername.getAttribute("value");
    }

    public void insertUser(String username,String password,String firstName,String lastName){
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys(password);
        inputFirstName.click();
        inputFirstName.clear();
        inputFirstName.sendKeys(firstName);
        inputLastName.click();
        inputLastName.clear();
        inputLastName.sendKeys(lastName);
        inputUsername.click();
        inputUsername.clear();
        inputUsername.sendKeys(username);
    }
    public void submitSignup(){
        signupSubmitButton.click();
    }

    public void clickBackToLoginUrl(){
        backToLoginUrl.click();
    }

    public String getSuccesAlertText(){
       return signupSuccessAlert.getText();
    }

    public String getUserExistsAlertText(){
        return signupUserExistsAlert.getText();
    }

}
