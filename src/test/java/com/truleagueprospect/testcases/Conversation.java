package com.truleagueprospect.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truleagueprospect.pages.ChatWindowConversation;
import com.truleagueprospect.pages.HomePage;
import com.truleagueprospectbase.Base;

public class Conversation extends Base {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod()
	public void setup() {

		driver = intializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));// coming from config.properties
																						// file
	}

	@Test(priority = 1)
	public void verifyUserisAbletoClickChat() {

		HomePage homePage = new HomePage(driver);

		homePage.clickOnChatWindow();
		ChatWindowConversation chatWindow = new ChatWindowConversation(driver);

		Assert.assertTrue(chatWindow.retriveButtonFromChatWindow());

	}
	
	@Test(priority =2)
	public void validateGoinfFromViewProfile() {
		HomePage homepage=new HomePage(driver);
		homepage.clickOnAmbassadorViewProfile();
	}

	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}

}
