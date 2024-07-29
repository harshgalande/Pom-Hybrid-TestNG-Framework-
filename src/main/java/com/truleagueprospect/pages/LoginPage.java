package com.truleagueprospect.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	
	
	WebDriverWait wait;

	@FindBy(name = "email")
	private WebElement emailAddressField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(xpath = "//button[text()=' Log in ']")
	private WebElement clickOnLoginButton;

	@FindBy(xpath = "//div[contains(text(),'Email is required.')]")
	private WebElement emailWarningNotMatching;

	@FindBy(xpath = "//div[contains(text(),'Password is required.')]")
	private WebElement passwordWarningNotMatching;

	@FindBy(linkText = "Forgot password?")
	private WebElement forgetPasswordLink;

	@FindBy(xpath = "//input[@placeholder='Enter your email']")
	private WebElement emailPlaceHolderMessage;

	@FindBy(xpath = "//input[@placeholder='Enter your password']")
	private WebElement passwordPlaceHolderMessage;

	@FindBy(xpath = "//img[@alt='open eye...']")
	private WebElement eyeButton;

	@FindBy(xpath = "//img[@alt='Close icon']")
	private WebElement LoginModalcrossIcon;

	@FindBy(xpath = "//span[text()=' Sign up']")
	private WebElement signupButton;

	@FindBy(xpath = "//span[text()='User does not exist.']")
	private WebElement userNotExistWarning;

	@FindBy(xpath = "//span[text()='Please enter correct password.  ']")
	private WebElement wrongPasswordErrorMessage;

	public LoginPage(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	}

//	public void enterEmailAddress(String emailText) {
//		emailAddressField.sendKeys(emailText);
//	}
//
//	public void enterPassword(String passwordText) {
//		passwordField.sendKeys(passwordText);
//
//	}
//
//	public AfterLoginAccountPage clickOnLoginButton() {
//
//		clickOnLoginButton.click();
//		return new AfterLoginAccountPage(driver);
//	}
	
	public AfterLoginAccountPage login(String emailText ,String passwordText) {
		
		emailAddressField.sendKeys(emailText);
		
		passwordField.sendKeys(passwordText);
		
		clickOnLoginButton.click();
		
		return new AfterLoginAccountPage(driver);

	}
	public String getAttributeValueForPasswordPageSource() {
		String passwordInpageSource = passwordField.getAttribute("value");
		return passwordInpageSource;
	}

	public String retriveEmailWarningMessageText() {
		String emailWarningText = emailWarningNotMatching.getText();
		return emailWarningText;
	}

	public String retrivePasswordWarningMessageText() {
		String passordWarningText = passwordWarningNotMatching.getText();

		return passordWarningText;
	}

	public ForgetPasswordPage clickOnForgetPassword() {
		forgetPasswordLink.click();

		return new ForgetPasswordPage(driver);
	}

	public String retriveEmailPlaceHolderText() {

		String emailPlaceHolderTextMessage = emailPlaceHolderMessage.getAttribute("placeholder");

		return emailPlaceHolderTextMessage;

	}

	public String retrivePasswordPlaceHolderText() {

		String passwordPlaceHolderTextMessage = passwordPlaceHolderMessage.getAttribute("placeholder");

		return passwordPlaceHolderTextMessage;
	}

	public void clickOnEyeButton() {
		eyeButton.click();
	}

	public WebElement clickOnCrossIcon() {
		LoginModalcrossIcon.click();
		return LoginModalcrossIcon;

	}

	public RegisterPage clickOnSignupButton() {
		signupButton.click();
		return new RegisterPage(driver);
//		return signupButton;
	}

	public String userNotExistWarning() {
		String warningMessage = userNotExistWarning.getText();
		return warningMessage;
	}

	public String passwordWrongErrorMessage() {

		String wrongPasswordError = wrongPasswordErrorMessage.getText();

		return wrongPasswordError;

	}
}
