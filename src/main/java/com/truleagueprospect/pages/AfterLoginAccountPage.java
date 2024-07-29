package com.truleagueprospect.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AfterLoginAccountPage {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//a[@role='link']//span")
	private WebElement viewProfileOption;
	
	

	@FindBy(xpath = "//span[text()=' Logout ']")
	private WebElement clickOnLogoutButton;
	
	@FindBy(xpath="//div[text()=' Yes, logout ']")
	private WebElement clickOnYesLogOut;
	
	public AfterLoginAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(30));

	}

	public boolean getDisplayStatusOfEditProfileOption() {
		wait.until(ExpectedConditions.visibilityOf(viewProfileOption));
		boolean displayStatus = viewProfileOption.isDisplayed();
		return displayStatus;

	}

	public WebElement getViewProfileOption() {
		
		
		return viewProfileOption;
	}
	
	public void clickOnProfileOption() {
		viewProfileOption.click();
	}

	public void clickOnLogoutButton() {
		clickOnLogoutButton.click();
	}
	
	public void clickOnYesLogoutButton() {
		clickOnYesLogOut.click();
	}
	
	
}
