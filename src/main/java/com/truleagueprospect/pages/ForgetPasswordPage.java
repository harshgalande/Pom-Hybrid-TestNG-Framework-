package com.truleagueprospect.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgetPasswordPage {

	WebDriver driver;

	@FindBy(xpath = "//div[text()='Forgot Password']")
	private WebElement verifyForgetPasswordPage;

	public ForgetPasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean retriveForgetPasswordText() {

		boolean fgtPasswordText = verifyForgetPasswordPage.isDisplayed();
		return fgtPasswordText;

	}
}
