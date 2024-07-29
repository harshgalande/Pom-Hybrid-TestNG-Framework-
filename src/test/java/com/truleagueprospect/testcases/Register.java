package com.truleagueprospect.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truleagueprospect.pages.AdditionalFieldsProspect;
import com.truleagueprospect.pages.HomePage;
import com.truleagueprospect.pages.RegisterPage;
import com.truleagueprospect.utiles.Utilites;
import com.truleagueprospectbase.Base;

public class Register extends Base {

	WebDriver driver;
	WebDriverWait wait;

	RegisterPage registerPage;
	AdditionalFieldsProspect additionalPage;

	@BeforeMethod()
	public void setup() {

		driver = intializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));// coming from config.properties
																						// file
		HomePage homepage = new HomePage(driver);
		homepage.clickOnLoginButton();

		registerPage = homepage.clickOnSignupButton();
	}

	@Test(priority = 1)
	public void verifyRegisterAnAccountByProvidingMandatoryFields() {

		registerPage.register(dataProp.getProperty("firstname"), 
				dataProp.getProperty("lastname"), Utilites.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		
		try {
		Thread.sleep(1000);
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		additionalPage=	registerPage.clickonSignUpButton();

		boolean secPage = additionalPage.retriveAdditionalFieldsPageText();

		boolean ambassadorList = additionalPage.retriveAmbassadorTextAfterSignUp();

		if (secPage) {

			Assert.assertEquals(additionalPage.retriveAdditionalFieldsPageText(),
					"Signup Not Done By User or Message is Not Coming for second page ! ");		

		} else if (ambassadorList) {
			Assert.assertTrue(additionalPage.retriveAmbassadorTextAfterSignUp(),
					"Profile Link is Not Coming Hence  User is not Able to Login!");
		} else {
			System.out.println("Both Conditions not matched or profile link is not available !");
		}
	}

	@Test(priority = 2)
	public void verifyLoginWithPuttingEmptySpacesIntheFirstNameField() throws InterruptedException {

		registerPage.firstNameField(" ");

		registerPage.privacyPolicykBox();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Click on the sign up button
		registerPage.clickonSignUpButton();

		Assert.assertEquals(registerPage.firstNameErrorMessage(), dataProp.getProperty("expFirstNameErrorMassage"),
				"Error Message is Not Coming in the FirstName Field ");

		Assert.assertEquals(registerPage.lastNameErrorMessage(), dataProp.getProperty("expLastNameErrorMassage"),
				"Error Message is Not Coming in the LaststName Field ");

		Assert.assertEquals(registerPage.retriveErrorMessage(), "Email is required",
				"Error Message is Not Coming in the Email Field ");

		Assert.assertEquals(registerPage.retrivePasswordErrorMessage(), dataProp.getProperty("exppasswordErrorMessage"),
				"Error Message is Not Coming in the Password Field ");

	}

	@Test(priority = 3)
	public void verifyLoginWithPuttingEmptySpacesIntheLastNameField() {

		registerPage.lastNameField(" ");

		registerPage.privacyPolicykBox();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Click on the sign up button
		registerPage.clickonSignUpButton();

		Assert.assertEquals(registerPage.firstNameErrorMessage(), dataProp.getProperty("expFirstNameErrorMassage"),
				"Error Message is Not Coming in the FirstName Field ");

		Assert.assertEquals(registerPage.lastNameErrorMessage(), dataProp.getProperty("expLastNameErrorMassage"),
				"Error Message is Not Coming in the LaststName Field ");

		Assert.assertEquals(registerPage.retriveErrorMessage(), "Email is required",
				"Error Message is Not Coming in the Email Field ");

		Assert.assertEquals(registerPage.retrivePasswordErrorMessage(), dataProp.getProperty("exppasswordErrorMessage"),
				"Error Message is Not Coming in the Password Field ");

	}

	@Test(priority = 4)
	public void verifyRegisteringAnAccountByLeavingBlankInFirstNameAndFillAlltheValidDetails() {

		registerPage.firstNameField("");
		registerPage.lastNameField(dataProp.getProperty("lastname"));
		registerPage.emailField(Utilites.generateEmailWithTimeStamp());
		registerPage.passwordField(prop.getProperty("validPassword"));
		registerPage.privacyPolicykBox();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Click on the sign up button
		registerPage.clickonSignUpButton();

		Assert.assertEquals(registerPage.firstNameErrorMessage(), dataProp.getProperty("expFirstNameErrorMassage"),
				"Error Message is Not Coming in the FirstName Field ");

	}

	@Test(priority = 5)
	public void verifyRegisteringAnAccountByLeavingBlankInLastNameAndFillAlltheValidDetails() {

		registerPage.firstNameField(dataProp.getProperty("firstname"));
		registerPage.lastNameField("");
		registerPage.emailField(Utilites.generateEmailWithTimeStamp());
		registerPage.passwordField(prop.getProperty("validPassword"));
		registerPage.privacyPolicykBox();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Click on the sign up button
		registerPage.clickonSignUpButton();

		Assert.assertEquals(registerPage.lastNameErrorMessage(), dataProp.getProperty("expLastNameErrorMassage"),
				"Error Message is Not Coming in the LaststName Field ");

	}

	@Test(priority = 6)
	public void verifyRegisteringAnAccountByLeavingBlankInFirstNameAndLastNameAndFillAlltheValidDetails() {

		registerPage.firstNameField("");
		registerPage.lastNameField("");
		registerPage.emailField(Utilites.generateEmailWithTimeStamp());
		registerPage.passwordField(prop.getProperty("validPassword"));
		registerPage.privacyPolicykBox();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Click on the sign up button
		registerPage.clickonSignUpButton();

		Assert.assertEquals(registerPage.firstNameErrorMessage(), dataProp.getProperty("expFirstNameErrorMassage"),
				"Error Message is Not Coming in the FirstName Field ");

		Assert.assertEquals(registerPage.lastNameErrorMessage(), dataProp.getProperty("expLastNameErrorMassage"),
				"Error Message is Not Coming in the LastName Field ");

	}

	@Test(priority = 7)
	public void ValidateRegisteringAnAccountByProvidingValidEmailAndDontEnterAnyFields() {

		registerPage.emailField(Utilites.generateEmailWithTimeStamp());

		registerPage.privacyPolicykBox();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Click on the sign up button
		registerPage.clickonSignUpButton();

		Assert.assertEquals(registerPage.firstNameErrorMessage(), dataProp.getProperty("expFirstNameErrorMassage"),
				"Error Message is Not Coming in the FirstName Field ");

		Assert.assertEquals(registerPage.lastNameErrorMessage(), dataProp.getProperty("expLastNameErrorMassage"),
				"Error Message is Not Coming in the LaststName Field ");

		Assert.assertEquals(registerPage.retrivePasswordErrorMessage(), dataProp.getProperty("exppasswordErrorMessage"),
				"Error Message is Not Coming in the Password Field ");

	}

	@Test(priority = 8)
	public void verifyRegisterWithWrongEmailId() {

		registerPage.firstNameField(dataProp.getProperty("firstname"));
		registerPage.lastNameField(dataProp.getProperty("lastname"));
		registerPage.emailField(dataProp.getProperty("wrongEmailIdText"));
		registerPage.passwordField(prop.getProperty("validPassword"));
		registerPage.privacyPolicykBox();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Click on the sign up button
		registerPage.clickonSignUpButton();

		Assert.assertEquals(registerPage.emailValidEmailError(), dataProp.getProperty("expValidEmailWarningMassage"),
				"Valid Email Id Warning message  is Not Showing in the Email Field!");
	}

	@Test(priority = 9)
	public void validateRegisteringAnAccountByProvidingNumberOnlyInEmailField() {

		registerPage.firstNameField(dataProp.getProperty("firstname"));
		registerPage.lastNameField(dataProp.getProperty("lastname"));
		registerPage.emailField(dataProp.getProperty("numbersInEmailIdField"));
		registerPage.passwordField(prop.getProperty("validPassword"));
		registerPage.privacyPolicykBox();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Click on the sign up button
		registerPage.clickonSignUpButton();

		Assert.assertEquals(registerPage.emailValidEmailError(), dataProp.getProperty("expValidEmailWarningMassage"),
				"Valid Email Id Warning message  is Not Showing in the Email Field!");

	}

	@Test(priority = 10)
	public void verifyRegisterWorkingFunctionllityEyeButton() {

		registerPage.passwordField(prop.getProperty("validPassword"));
		registerPage.eyeButtonClick();

	}

	@Test(priority = 11)
	public void validateAlltheMandatoryFieldsInRegisterProperPlaceHolder() {

		Assert.assertEquals(registerPage.retriveFirstNamePlaceholder(),
				dataProp.getProperty("expFirstNamePlaceHolderText"),
				"FirstName Place holder is Not Coming in the placeholder!");

		Assert.assertEquals(registerPage.retriveLastNamePlaceholder(),
				dataProp.getProperty("expLastNamePlaceHolderText"),
				"LastName Place holder is Not Coming in the placeholder!");

		Assert.assertEquals(registerPage.retriveEmailPlaceholder(), dataProp.getProperty("expEmailPlaceholderText"),
				"Email Place holder is Not Coming in the placeholder!");

		Assert.assertEquals(registerPage.retrivePasswordPlaceholder(),
				dataProp.getProperty("expPasswordPlaceHolderText"),
				"Password Place holder is Not Coming in the placeholder!");

	}

	@Test(priority = 12)
	public void verifyWhetherThePrivacyPolicyCheckBoxIsSelectedByDefault() {

		WebElement privacyPolicyCheckbox = registerPage.verifyPrivacyPolicyCheckBoxisSelected();
		Assert.assertFalse(privacyPolicyCheckbox.isSelected(),
				"Not Working Privacy Policy Check Box is Selected By Defult!");

		// If the assertion passes, the following line will be executed
		System.out.println("Test Passed: 'Privacy Policy' checkbox is not selected by default.");
	}

	@Test(priority = 13)
	public void validateRegisterAnAccountByUsingKeyBoard() {

		registerPage.goingFromKeyBoardEvents();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Click on the sign up button
		additionalPage = registerPage.clickonSignUpButton();

		boolean secPage = additionalPage.retriveAdditionalFieldsPageText();

		boolean ambassadorList = additionalPage.retriveAmbassadorTextAfterSignUp();

		if (secPage) {

			Assert.assertEquals(additionalPage.retriveAdditionalFieldsPageText(),
					"Signup Not Done By User or Message is Not Coming for second page ! ");

		} else if (ambassadorList) {
			Assert.assertTrue(additionalPage.retriveAmbassadorTextAfterSignUp(),
					"Profile Link is Not Coming Hence  User is not Able to Login!");
		} else {
			System.out.println("Both Conditions not matched or profile link is not available !");
		}

	}

	@AfterMethod()
	public void tearDown() {

		driver.quit();
	}
}
