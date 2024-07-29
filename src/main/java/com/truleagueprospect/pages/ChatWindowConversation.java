package com.truleagueprospect.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChatWindowConversation {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//div[@title='view details']")
	private WebElement verifyChatRedirectionSuccesfull;
	


	public ChatWindowConversation(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}

	
	  public boolean retriveButtonFromChatWindow() {
	        return verifyChatRedirectionSuccesfull.isDisplayed();
	    }
	  

}
