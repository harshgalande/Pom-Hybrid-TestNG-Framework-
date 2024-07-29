package com.truleagueprospect.pages;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.truleagueprospect.utiles.Utilites;
import com.truleagueprospectbase.Base;

public class RegisterPage extends Base {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//div[text()='Sign up']")
	private WebElement verifyGoingintoSignupPage;

	@FindBy(name = "first_name")
	private WebElement firstNameField;

	@FindBy(name = "last_name")
	private WebElement lastNameField;

	@FindBy(name = "email")
	private WebElement emailField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(xpath = "//label[@for='mat-checkbox-1-input']")
	private WebElement checkBoxOption;

	@FindBy(xpath = "//button[text()=' Sign up ']")
	private WebElement signUpButton;

	@FindBy(xpath = "//div[text()=' First name is required ']")
	private WebElement fNameErrorMessage;

	@FindBy(xpath = "//div[text()=' Last name is required ']")
	private WebElement LNameErrorMessage;

	@FindBy(xpath = "//div[text()=' Email is required ']")
	private WebElement emailErrorMessage;

	@FindBy(xpath = "//div[text()=' Password is required ']")
	private WebElement passwordErrorMessage;

	@FindBy(xpath = "//div[text()=' Please enter valid email. ']")
	private WebElement validEmailErrorMessage;

	@FindBy(xpath = "//img[@alt='eyeopen...']")
	private WebElement eyeButton;

	@FindBy(xpath = "//input[@placeholder='Enter your first name']")
	private WebElement firstNamePlaceHolderText;

	@FindBy(xpath = "//input[@placeholder='Enter your last name']")
	private WebElement lastNamePlaceHolderText;

	@FindBy(xpath = "//input[@placeholder='Enter your email']")
	private WebElement emaillaceHolderText;

	@FindBy(xpath = "//input[@placeholder='Enter your password']")
	private WebElement passwordPlaceHolderText;

	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	public boolean verifyandRetriveRedirectionOfSignupPage() {
		wait.until(ExpectedConditions.visibilityOf(verifyGoingintoSignupPage));
		boolean redirectionofSignupPage = verifyGoingintoSignupPage.isDisplayed();
		return redirectionofSignupPage;
	}
	
	public void register(String firstName,String lastName,String email,String password ) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		checkBoxOption.click();
		}
	
	public void firstNameField(String firstName) {
		firstNameField.sendKeys(firstName);
	}

	public void lastNameField(String lastName) {
		lastNameField.sendKeys(lastName);
	}

	public void emailField(String email) {
		emailField.sendKeys(email);
	}

	public void passwordField(String password) {
		passwordField.sendKeys(password);
	}

	public void privacyPolicykBox() {
		checkBoxOption.click();
	}

	public AdditionalFieldsProspect clickonSignUpButton() {
		signUpButton.click();
		return new AdditionalFieldsProspect(driver);
	}

	public String firstNameErrorMessage() {
		String fname = fNameErrorMessage.getText();
		return fname;
	}

	public String lastNameErrorMessage() {
		String lName = LNameErrorMessage.getText();
		return lName;
	}

	public String retriveErrorMessage() {
		String emailError = emailErrorMessage.getText();
		return emailError;
	}

	public String retrivePasswordErrorMessage() {
		String pwdError = passwordErrorMessage.getText();
		return pwdError;
	}

	public String emailValidEmailError() {

		String EmailError = validEmailErrorMessage.getText();
		return EmailError;
	}

	public void eyeButtonClick() {
		eyeButton.click();

	}

	public String retriveFirstNamePlaceholder() {
		String placeHolderFName = firstNamePlaceHolderText.getAttribute("placeholder");
		return placeHolderFName;
	}

	public String retriveLastNamePlaceholder() {
		String placeHolderLName = lastNamePlaceHolderText.getAttribute("placeholder");
		return placeHolderLName;
	}

	public String retriveEmailPlaceholder() {
		String placeHolderEmail = emaillaceHolderText.getAttribute("placeholder");
		return placeHolderEmail;
	}

	public String retrivePasswordPlaceholder() {
		String placeHolderPassword = passwordPlaceHolderText.getAttribute("placeholder");
		return placeHolderPassword;
	}

	public WebElement verifyPrivacyPolicyCheckBoxisSelected() {

		WebElement checkPrivacyAndPolicy = checkBoxOption;
		return checkPrivacyAndPolicy;
	}

	public void goingFromKeyBoardEvents() {

		firstNameField.sendKeys(dataProp.getProperty("firstname"), Keys.TAB, dataProp.getProperty("lastname"), Keys.TAB,
				Utilites.generateEmailWithTimeStamp(), Keys.TAB, prop.getProperty("validPassword"));
		checkBoxOption.click();

	}

}
