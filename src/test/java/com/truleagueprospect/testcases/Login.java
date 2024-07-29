package com.truleagueprospect.testcases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.truleagueprospect.pages.AfterLoginAccountPage;
import com.truleagueprospect.pages.ForgetPasswordPage;
import com.truleagueprospect.pages.HomePage;
import com.truleagueprospect.pages.LoginPage;
import com.truleagueprospect.pages.RegisterPage;
import com.truleagueprospect.pages.ViewProfilePage;
import com.truleagueprospect.utiles.Utilites;
import com.truleagueprospectbase.Base;

public class Login extends Base {

	WebDriver driver;
	WebDriverWait wait;

	LoginPage loginPage;
	AfterLoginAccountPage accountPage;
	RegisterPage registerPage;
	ForgetPasswordPage fgtpassword;
	ViewProfilePage profile;

	public Login() {
		super(); // for loading the properties file
	}

	@BeforeMethod()
	public void setup() {

		driver = intializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		loginPage = homepage.clickOnLoginButton();
	}

	@Test(priority = 1, dataProvider = "supplyTestData")
	public void verifyLoginWithValidCredenetials(String email, String password) {

		accountPage = loginPage.login(email, password);

		// Implemented Waiting Mechanism for Wait Until Element comes.....

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(accountPage.getViewProfileOption()));

		Assert.assertTrue(accountPage.getDisplayStatusOfEditProfileOption(),
				"Profile Link is Not Coming Hence  User is not Able to Login!");
	}

	@DataProvider
	public Object[][] supplyTestData() {

		Object[][] data = Utilites.getTestDataFromExcelFile("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCreadentials() {

		loginPage.login(Utilites.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));

		String actualwrongInvalidEmailCredentilas = loginPage.userNotExistWarning();
		String expectedInvalidEmailCredentials = dataProp.getProperty("expectedInvalidEmailCredentials");

		assert actualwrongInvalidEmailCredentilas.equals(expectedInvalidEmailCredentials)
				: "Error Message is Not Coming ";
	}

	@Test(priority = 3)
	public void verifyLoginWithInValidEmailAndValidPassword() {
		loginPage.login(Utilites.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));

		String actualwrongInvalidEmailCredentilas = loginPage.userNotExistWarning();
		String expectedInvalidEmailCredentials = dataProp.getProperty("expectedInvalidEmailCredentials");

		assert actualwrongInvalidEmailCredentilas.equals(expectedInvalidEmailCredentials)
				: "Error Message is Not Coming ";

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInValidPassword() {

		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));

		String actualValidPasswordWarningMessage = loginPage.passwordWrongErrorMessage();
		String expectedValidPasswordWarningMessage = dataProp.getProperty("expectedInvalidPasswordWarning");

		assert actualValidPasswordWarningMessage.equals(expectedValidPasswordWarningMessage)
				: "Valid Password Error Message is Not Coming in the password";
	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {

		loginPage.login("", "");

		assert loginPage.retriveEmailWarningMessageText().contains(dataProp.getProperty("expectedEmailWarningMessage"))
				: "Warning Message is not Coming for the Emaild Field!";
		assert loginPage.retrivePasswordWarningMessageText()
				.contains(dataProp.getProperty("expectedPasswordWarningMessage"))
				: "Warning Message is not Coming in the Password Field!";

	}

	@Test(priority = 6)
	public void verifyForgotPasswordLinkAvailableandRedirecting() {

		fgtpassword = loginPage.clickOnForgetPassword();

		Assert.assertTrue(fgtpassword.retriveForgetPasswordText(), "User is Not Able to Redirect to Forgot password!");

	}

	@Test(priority = 7)
	public void verifyEmailAndPasswordTextFieldsLoginHavePlaceHolderText() {

		assert loginPage.retriveEmailPlaceHolderText().equals(dataProp.getProperty("expectedEmailPlaceHolderText"))
				: "Placeholder text is Not Coming in the Email id Text Field !";
		assert loginPage.retrivePasswordPlaceHolderText()
				.equals(dataProp.getProperty("expectedPasswordPlaceHolderText"))
				: "Placeholder text is not coming in the Password Text Field!";
	}

	@Test(priority = 8)
	public void verifyLoginIntoApplicationClickingBrowsingBackButton() {

		accountPage = loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
		driver.navigate().back();

		// Implemented Waiting Mechanism for Wait Until Element comes.....

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(accountPage.getViewProfileOption()));

		Assert.assertTrue(accountPage.getDisplayStatusOfEditProfileOption(),
				"Profile Link is Not Coming Hence  User is not Able to Login!");
	}

	@Test(priority = 9)
	public void verifyLoginIntoApplicationClickingLogoutAndBrowsingBackButton() {

		accountPage = loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));

		Assert.assertTrue(accountPage.getDisplayStatusOfEditProfileOption(),
				"Profile Link is Not Coming Hence  User is not Able to Login!");

		accountPage.clickOnProfileOption();
		accountPage.clickOnLogoutButton();
		accountPage.clickOnYesLogoutButton();
		driver.navigate().back();

	}

	@Test(priority = 10)
	public void verifyWorkingOfEyeIcon() {

		accountPage = loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
		loginPage.clickOnEyeButton();

		Assert.assertTrue(accountPage.getDisplayStatusOfEditProfileOption(),
				"Profile Link is Not Coming Hence  User is not Able to Login!");

	}

	@Test(priority = 11)
	public void veriyLoginPageCrossButtonWokingOrNot() {

		loginPage.clickOnCrossIcon();
	}

	@Test(priority = 12)
	public void verifyLoginRedirectionToSignUpPage() {

		registerPage = loginPage.clickOnSignupButton();

		Assert.assertTrue(registerPage.verifyandRetriveRedirectionOfSignupPage(),
				"Redirection did not happen from Login to Sign up or text is not displayed");
	}

	@Test(priority = 13)
	public void verifyLoginIntoApplicationUsingCapsLock() {

		accountPage = loginPage.login(prop.getProperty("validEmailWithCaps"),
				prop.getProperty("validPasswordWithCaps"));
	}

	@Test(priority = 14)
	public void verifyLoginIntoApplicationGoingFromViewProfile() {

		driver.navigate().refresh();

		HomePage homepage = new HomePage(driver);

		profile = homepage.clickOnAmbassadorViewProfile();

		profile.waitForAmbassadorsProfileTextComing();

		profile.clickOnChatButton();

		profile.clickOnFromViewProfileLoginHereButton();

		accountPage = loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));

		Assert.assertTrue(accountPage.getDisplayStatusOfEditProfileOption(),
				"Profile Link is Not Coming Hence  User is not Able to Login!");

//			driver .close();
//			setup(); method calling statement..........

	}

	@Test(priority = 15)
	public void verifyThePasswordIsNotVisibleInPageSource() {

		accountPage = loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("passwordVisibility"));

		// Verify if the entered password is not visible in the page source
		Assert.assertFalse(driver.getPageSource().contains(loginPage.getAttributeValueForPasswordPageSource()),
				"Entered password is visible in the page source.");
	}

	@Test(priority = 16)
	public void verifyNavagatingLoginPageFromChatWindow() {

		driver.navigate().refresh();

		HomePage homePage = new HomePage(driver);

		homePage.clickOnChatWindow();

		homePage.clickOnLoginButton();

		accountPage = loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));

		Assert.assertTrue(accountPage.getDisplayStatusOfEditProfileOption(),
				"Profile Link is Not Coming Hence  User is not Able to Login!");
	}

	@Test(priority = 17)
	public void pageHeadingTititle() {

		driver.navigate().refresh();
		Assert.assertEquals(driver.getTitle(), dataProp.getProperty("expectedTitle"), "Page title is incorrect.");

		Assert.assertEquals(driver.getCurrentUrl(), dataProp.getProperty("expectedURL"), "Page URL is incorrect.");
	}

	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}

}
