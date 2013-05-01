package org.jsystemtest.teamworkso;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPageObject {
	
	public static final String LOGIN_TITLE = "TopQ";
	
	@FindBy(id = "userLogin")
	private WebElement userTb;

	@FindBy(id = "password")
	private WebElement passwordTb;

	@FindBy(id = "submitBtn")
	private WebElement loginBtn;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void setUserName(String user) {
		userTb.sendKeys(user);
	}

	public void setPassword(String password) {
		passwordTb.sendKeys(password);
	}

	public DashboardPage login() {
		loginBtn.click();
		return new DashboardPage(driver);
	}
}
