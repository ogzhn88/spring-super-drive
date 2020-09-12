package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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



	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		baseUrl = "http://localhost:" + port;

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
	public void signupAndLogin() throws InterruptedException {
		driver.get(baseUrl + "/login");
		WebDriverWait wait = new WebDriverWait(driver, 3);
		loginPage = new LoginPage(driver);
		WebElement loginMarker = wait.until(webDriver -> webDriver.findElement(By.id("loginButton")));
		loginPage.clickSignup();
		signupPage = new SignupPage(driver);
		Thread.sleep(2000);
		signupPage.insertUser("ogzhn","ogz123","oguzhan","unalir");
		signupPage.submitSignup();
		Assertions.assertEquals("You successfully signed up! Please continue to the login page.",signupPage.getSuccesAlertText());
		Thread.sleep(3000);
		signupPage.clickBackToLoginUrl();
		WebElement loginMarker2 = wait.until(webDriver -> webDriver.findElement(By.id("loginButton")));
		loginPage.insertLoginCredentials("ogzhn","ogz123");
		loginPage.submitLogin();
	}

}
