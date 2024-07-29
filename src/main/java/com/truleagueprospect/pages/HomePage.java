package com.truleagueprospect.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	// Objects
	@FindBy(linkText = "Log in")
	private WebElement loginOption;
	

	@FindBy(xpath = "//span[text()=' Sign up']")
	private WebElement signupButton;
	
	
	
	@FindBy(xpath="(//span[@id='view-profile-span-0'])[1]")
	private WebElement clickOnViewProfile;
	
	@FindBy(xpath="(//span[contains(@aria-label,'Chat with')])[1]")
	private WebElement clickOnChatWithdynEle; 

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public LoginPage clickOnLoginButton() {
		loginOption.click();
		return new LoginPage(driver); 
	}
	
	public ViewProfilePage clickOnAmbassadorViewProfile() {
		clickOnViewProfile.click();
		return new ViewProfilePage(driver);
	}

	public RegisterPage clickOnSignupButton() {
		signupButton.click();
//		return signupButton;
		return new RegisterPage(driver);
	}
	
	public void clickOnChatWindow() {
		clickOnChatWithdynEle.click();
	}

}
