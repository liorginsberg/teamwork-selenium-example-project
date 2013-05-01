package org.jsystemtest.teamworkso;

import java.util.concurrent.TimeUnit;

import jsystem.extensions.report.html.Report;
import jsystem.framework.report.ReportElement;
import jsystem.framework.report.Reporter;
import jsystem.framework.system.SystemObjectImpl;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Web extends SystemObjectImpl {

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

	private WebDriver driver;
	private String browserType;

	public void init() throws Exception {
		super.init();
		Assert.assertNotNull("Please define the browser type member in the SUT file", browserType);
		setBrowserType();
		Assert.assertNotNull("Could not initiate driver", driver);
		Assert.assertNotNull("Could not initiate driver", url);
		driver.get(url);
	}

	public LoginPage getLoginPage() {
		return new LoginPage(driver);
	}

	public void quit() {
		driver.quit();
	}

	public void getCurrentPage() {
		driver.getTitle();
	}

	public void waitforPageLoad() {

	}

	/**
	 * 
	 * @param timeout
	 * @param unit
	 */
	public void setImplicitWait(long timeout, TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}

	public boolean isTextExistOnPage(String text) {
		Boolean isFound = false;
		try {
			WebElement element = driver.findElement(By.xpath("//*[contains(.,'" + text + "')]"));
			isFound = element != null;
		} catch (Exception e) {
			System.out.println("could not find element with text" + text);
		}
		return isFound;
	}

	public void setBrowserType() {
		report.report("setting the driver browser");
		if (browserType.toUpperCase().equals("IE") || browserType.toUpperCase().equals("INTERNET EXPLORER")) {
			driver = new InternetExplorerDriver();
		} else if (browserType.toUpperCase().equals("FIREFOX")) {
			driver = new FirefoxDriver();
		} else if (browserType.toUpperCase().equals("CHROME")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
			driver = new ChromeDriver();
		}
	}
}
