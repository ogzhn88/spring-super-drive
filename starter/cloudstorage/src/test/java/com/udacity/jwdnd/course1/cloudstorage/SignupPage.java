package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
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

    public void insertUser(WebDriver driver,String username,String password,String firstName,String lastName){
        driver.findElement(By.id("inputPasswordSignup"));
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys(password);
        driver.findElement(By.id("inputFirstName"));
        inputFirstName.click();
        inputFirstName.clear();
        inputFirstName.sendKeys(firstName);
        driver.findElement(By.id("inputLastName"));
        inputLastName.click();
        inputLastName.clear();
        inputLastName.sendKeys(lastName);
        driver.findElement(By.id("inputUsernameSignup"));
        inputUsername.click();
        inputUsername.clear();
        inputUsername.sendKeys(username);
    }
    public void submitSignup(){
        signupSubmitButton.click();
    }

    public String getUserExistsAlertText(){
        return signupUserExistsAlert.getText();
    }



}
