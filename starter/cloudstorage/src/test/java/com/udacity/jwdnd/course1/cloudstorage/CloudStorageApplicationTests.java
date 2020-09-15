package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;
	private  String baseUrl;
	private LoginPage loginPage;
	private SignupPage signupPage;
	private HomePage homePage;
	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.firefoxdriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new FirefoxDriver();
		baseUrl = "http://localhost:" + port;
		signupPage = new SignupPage(driver);
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		driver.get(baseUrl + "/signup");
		signupPage.insertUser(driver,"admin","admin","admin","admin");
		signupPage.submitSignup();

	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get(baseUrl+ "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void unauthorizedOnlyLoginAndSignup(){
		driver.get(baseUrl + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
		loginPage = new LoginPage(driver);
		loginPage.clickSignup();
		Assertions.assertEquals("Sign Up",driver.getTitle());
	}
	@Test
	public void signupLoginAndLogout() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get(baseUrl + "/login");
		loginPage.clickSignup();
		signupPage.insertUser(driver,"ogzhn","ogz123","oguzhan","unalir");
		signupPage.submitSignup();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("signupSuccessAlert"))));
		Assertions.assertEquals("You successfully signed up! You can login below.",loginPage.getSuccesAlertText());
		loginPage.insertLoginCredentials("ogzhn","ogz123");
		loginPage.submitLogin();
		Assertions.assertEquals("Home", driver.getTitle());
		homePage.clickLogoutButton();
	}
	@Test
	public void createEditAndDeleteNote() {
		driver.get(baseUrl + "/login");
		WebDriverWait wait = new WebDriverWait(driver, 10);
        loginPage.insertLoginCredentials("admin","admin");
        loginPage.submitLogin();
        homePage.clickNavTab();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("addNoteButton"))));
		homePage.clickaddNoteButton();
		homePage.insertNote("MY NOTE","MY DESCRIPTION");
		homePage.clickSaveNotButton();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("nsaved"))));
		Assertions.assertEquals("Note saved successfully",homePage.getNoteSavedAlertText());
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("noteTable"))));
		Assertions.assertEquals(true,homePage.noteExistsInTable("MY NOTE","MY DESCRIPTION"));
		homePage.clickNoteEditButton("MY NOTE","MY DESCRIPTION");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("saveNoteButton"))));
		homePage.insertNote("MY EDITED NOTE","MY NEW DESCRIPTION");
		homePage.clickSaveNotButton();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("nedited"))));
		Assertions.assertEquals("Note edited successfully",homePage.getNoteEditedAlertText());
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("noteTable"))));
		homePage.clickLatestDeleteNoteButton();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ndeleted"))));
		Assertions.assertEquals("Note deleted succesfully",homePage.getNoteDeletedAlertText());
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("noteTable"))));
		Assertions.assertEquals(false,homePage.noteExistsInTable("MY EDITED NOTE","MY NEW DESCRIPTION"));
	}

	@Test
	public void createEditAndDeleteCredentials()  {
		driver.get(baseUrl + "/login");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		loginPage.insertLoginCredentials("admin","admin");
		loginPage.submitLogin();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("nav-credentials-tab"))));
		homePage.clickCredentialsTab();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("addCrButton"))));
		homePage.clickCredentialsAddButton();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("credentialSubmitButton"))));
        homePage.insertCredentials("www.dummypage.com","oğuzhan","123");
        homePage.clickCredentialSubmitButton();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credentialSaved"))));
		Assertions.assertEquals("Credential saved successfully",homePage.getCredentialSaveSuccessAlertText());
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credentialTable"))));
		Assertions.assertEquals(true,homePage.credentialExistsInTableWithEncPass("www.dummypage.com","oğuzhan","123"));
		homePage.clickCredentialEditButton("www.dummypage.com","oğuzhan","123");
		Assertions.assertEquals(true,homePage.checkPasswordisVisibleinModal("123"));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credentialSubmitButton"))));
		homePage.insertCredentials("www.newpage.com","udacity","455");
		homePage.clickCredentialSubmitButton();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credentialTable"))));
		Assertions.assertEquals(true,homePage.credentialExistsInTableWithEncPass("www.newpage.com","udacity","455"));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("cedited"))));
		Assertions.assertEquals("Credential edited successfully",homePage.getCredentialEditSuccessAlertText());
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credentialTable"))));
		homePage.clickCredentialDeleteButton("www.newpage.com","udacity","455");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("cdeleted"))));
		Assertions.assertEquals("Credential deleted successfully",homePage.getCredentialDeleteSuccessAlertText());
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credentialTable"))));
		Assertions.assertEquals(false,homePage.credentialExistsInTableWithEncPass("www.newpage.com","udacity","455"));

	}

}
