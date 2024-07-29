package com.truleagueprospect.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewProfilePage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="//div[text()='Ambassador’s Profile']")
	private WebElement ambassadorText;
	
	@FindBy(xpath="//div[@type='button']//img[@alt='Chat']")
	WebElement clickOnViewProfileChatButton;
	
	@FindBy(xpath="//span[text()=' Log in here']")
	WebElement ClickOnLoginHereButton;
	
	public ViewProfilePage(WebDriver driver){
		this.driver=driver;
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}
	
	
	 // Method to wait for the 'Ambassador’s Profile' element to be visible
    public void waitForAmbassadorsProfileTextComing() {
        wait.until(ExpectedConditions.visibilityOf(ambassadorText));
    }
    
    public void clickOnChatButton() {
    	clickOnViewProfileChatButton.click();
    }
    
    public WebElement  clickOnFromViewProfileLoginHereButton() {
    	ClickOnLoginHereButton.click();
    	return ClickOnLoginHereButton;
    	
    }
}
