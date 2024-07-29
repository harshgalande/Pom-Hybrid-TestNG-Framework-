package com.truleagueprospect.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdditionalFieldsProspect {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//div[text()=' Ambassadors ']")
	private WebElement retriveifAttrOffAdditionalFieldText;

	@FindBy(xpath = "//div[text()=' Sign up successful. ']")
	private WebElement additionalFieldsecPage;

	public AdditionalFieldsProspect(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		
	}

	public boolean retriveAdditionalFieldsPageText() {
		wait.until(ExpectedConditions.visibilityOf(additionalFieldsecPage));
		boolean retriveTextAttOff=additionalFieldsecPage.isDisplayed();
		return retriveTextAttOff;
	}

	public boolean retriveAmbassadorTextAfterSignUp() {
		boolean retriveText = retriveifAttrOffAdditionalFieldText.isDisplayed();
		return retriveText;
	}
}
