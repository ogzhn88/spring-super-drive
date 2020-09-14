package com.udacity.jwdnd.course1.cloudstorage;

import com.sun.tools.jconsole.JConsoleContext;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }


    @FindBy(id="logoutButton")
    private WebElement logoutButton;

    @FindBy(id="nav-notes-tab")
    private WebElement navTab;

    @FindBy(id="addNoteButton")
    private WebElement addNoteButton;

    @FindBy(id="note-title")
    private WebElement noteTitle;

    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id="saveNoteButton")
    private WebElement saveNoteButton;

    @FindBy(id="noteTable")
    private WebElement noteTable;

    @FindBy(className="noteDeleteButton")
    private List<WebElement> noteDeleteButtons;

    @FindBy(id="nsaved")
    private WebElement noteSavedSuccessAlert;

    @FindBy(id="ndeleted")
    private WebElement noteDeletedSuccessAlert;

    @FindBy(className="noteEditBtn")
    private List<WebElement> noteEditButtons;

    @FindBy(id="nedited")
    private WebElement noteEditedSuccessAlert;

    @FindBy(id="nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id="addCrButton")
    private WebElement addCrButton;

    @FindBy(id="credential-url")
    private WebElement credentilUrlInput;

    @FindBy(id="credential-username")
    private WebElement credentilUserInput;

    @FindBy(id="credential-password")
    private WebElement credentilPasswordInput;

    @FindBy(id="credentialSubmitButton")
    private WebElement credentialSubmitButton;

    @FindBy(id="credentialTable")
    private WebElement credentialTable;

    @FindBy(id="credentialSaved")
    private WebElement credentialSaveSuccessAlert;

    @FindBy(id="cedited")
    private WebElement credentialEditSuccessAlert;

    @FindBy(id="cdeleted")
    private WebElement credentialDeleteSuccessAlert;



    public void clickLogoutButton(){
        logoutButton.click();
    }

    public void clickNavTab(){
        navTab.click();
    }

    public void clickaddNoteButton(){
        addNoteButton.click();
    }
    public String getNoteTitle(){
       return noteTitle.getAttribute("value");
    }

    public String getNoteDescription(){
        return noteDescription.getAttribute("value");
    }

    public void clickSaveNotButton(){
        saveNoteButton.click();
    }

    public void  insertNote(String title,String desc){
        noteTitle.click();
        noteTitle.clear();
        noteTitle.sendKeys(title);
        noteDescription.click();
        noteDescription.clear();
        noteDescription.sendKeys(desc);
    }

    public boolean noteExistsInTable(String title,String desc){

        List<WebElement> tableElements = noteTable.findElements(By.tagName("tbody"));
        List<String> tableThs = new ArrayList();
        List<String> tableTds = new ArrayList();


        for (int i=0; i <tableElements.size();i++){
            WebElement tableElement  = tableElements.get(i);
         List<WebElement> rowThs = tableElement.findElements(By.tagName("th"));
         List<WebElement> rowTds = tableElement.findElements(By.tagName("td"));
            for (WebElement rowTh: rowThs) {
                tableThs.add(rowTh.getText());
            }
            for (WebElement rowTd: rowTds) {
                 tableTds.add(rowTd.getText());
            }
        }

        if(tableThs.contains(title) && tableTds.contains(desc))
            return true;
        else
            return false;

    }


    public  void clickNoteEditButton(String title,String desc){
        List<WebElement> editButtons =  noteEditButtons;
        for (WebElement editButton: editButtons ) {

            if(editButton.getAttribute("data-title").equals(title) && editButton.getAttribute("data-description") .equals(desc) ){
                editButton.click();
                break;
            }else{
                continue;
            }

        }
    }


    public void clickLatestDeleteNoteButton(){
        WebElement latestDeleteButton = noteDeleteButtons.get(noteDeleteButtons.size()-1);
        latestDeleteButton.click();
    }

    public String getNoteSavedAlertText(){

        String message = noteSavedSuccessAlert.findElement(By.tagName("span")).getText();
        return message;
    }

    public String getNoteDeletedAlertText(){
        String message = noteDeletedSuccessAlert.findElement(By.tagName("span")).getText();
        return message;
    }

    public String getNoteEditedAlertText(){
        String message = noteEditedSuccessAlert.findElement(By.tagName("span")).getText();
        return message;
    }

    public void clickCredentialsTab(){
        credentialsTab.click();
    }

    public void clickCredentialsAddButton(){
        addCrButton.click();
    }

   public void insertCredentials(String url, String username,String password){

        credentilUrlInput.click();
        credentilUrlInput.clear();
        credentilUrlInput.sendKeys(url);
        credentilUserInput.click();
        credentilUserInput.clear();
        credentilUserInput.sendKeys(username);
        credentilPasswordInput.click();
        credentilPasswordInput.clear();
        credentilPasswordInput.sendKeys(password);

   }

    public void clickCredentialSubmitButton(){
        credentialSubmitButton.click();
    }


    public boolean credentialExistsInTableWithEncPass(String url,String username,String password){

        List<WebElement> tableRows= credentialTable.findElements(By.tagName("tbody"));
        List<WebElement> myTrs= new ArrayList();

            for (WebElement tableRow: tableRows) {
                 if(!tableRow.getText().isBlank() && !tableRow.getText().isEmpty()){
                     WebElement element = tableRow.findElement(By.tagName("tr"));
                     myTrs.add(element);
                 }
            }


        for (WebElement tableRow: myTrs) {

            if(tableRow.findElement(By.className("urlTd")).getText().equals(url) && tableRow.findElement(By.className("usernameTd")).getText().equals(username)
                    && !tableRow.findElement(By.className("encpasswordTd")).getText().isEmpty() &&
                    !tableRow.findElement(By.className("encpasswordTd")).getText().isBlank() &&
                    !tableRow.findElement(By.className("encpasswordTd")).getText().equals(password)){

                return  true;

            }
        }

        return false;

    }


    public void clickCredentialEditButton(String url,String username,String password){

        List<WebElement> tableRows= credentialTable.findElements(By.tagName("tbody"));
        List<WebElement> myTrs= new ArrayList();
        for (WebElement tableRow: tableRows) {
            if(!tableRow.getText().isBlank() && !tableRow.getText().isEmpty()){
                WebElement element = tableRow.findElement(By.tagName("tr"));
                myTrs.add(element);
            }
        }

        for (WebElement tableRow: myTrs) {

            if(tableRow.findElement(By.className("urlTd")).getText().equals(url) && tableRow.findElement(By.className("usernameTd")).getText().equals(username)
                    && !tableRow.findElement(By.className("encpasswordTd")).getText().isEmpty() &&
                    !tableRow.findElement(By.className("encpasswordTd")).getText().isBlank() &&
                    !tableRow.findElement(By.className("encpasswordTd")).getText().equals(password)){

                  tableRow.findElement(By.className("credentialEditBtn")).click();
            }
        }


    }

    public String getCredentialSaveSuccessAlertText(){

        String message = credentialSaveSuccessAlert.findElement(By.tagName("span")).getText();
        return message;
    }

    public String getCredentialEditSuccessAlertText(){

        String message = credentialEditSuccessAlert.findElement(By.tagName("span")).getText();
        return message;
    }

    public boolean checkPasswordisVisibleinModal(String password){

        if(credentilPasswordInput.getAttribute("value").toString().equals(password)){
            return true;
        }

        return false;

    }


    public void clickCredentialDeleteButton(String url,String username,String password){

        List<WebElement> tableRows= credentialTable.findElements(By.tagName("tbody"));
        List<WebElement> myTrs= new ArrayList();
        for (WebElement tableRow: tableRows) {
            if(!tableRow.getText().isBlank() && !tableRow.getText().isEmpty()){
                WebElement element = tableRow.findElement(By.tagName("tr"));
                myTrs.add(element);
            }
        }

        for (WebElement tableRow: myTrs) {

            if(tableRow.findElement(By.className("urlTd")).getText().equals(url) && tableRow.findElement(By.className("usernameTd")).getText().equals(username)
                    && !tableRow.findElement(By.className("encpasswordTd")).getText().isEmpty() &&
                    !tableRow.findElement(By.className("encpasswordTd")).getText().isBlank() &&
                    !tableRow.findElement(By.className("encpasswordTd")).getText().equals(password)){

                tableRow.findElement(By.className("deleteCredentialBtn")).click();
            }
        }


    }

    public String getCredentialDeleteSuccessAlertText(){

        String message = credentialDeleteSuccessAlert.findElement(By.tagName("span")).getText();
        return message;
    }

}
