package org.jsystemtest.teamworkso;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends AbstractTeamworkPmPage {

	public static final String LOGIN_TITLE = "Dashboard - TopQ";

	@FindBy(xpath = "//a[@title='My First Project']")
	private WebElement clickOnProject;

	@FindBy(xpath = "//a[text()='Tasks']")
	private WebElement tasksBtn;

	@FindBy(xpath = "//div[@id='options126599']//button[text()='Add task']")
	private WebElement addTasksBtn;

	@FindBy(xpath="//input[@name='taskName']")
	private WebElement addTaskText;
	
	@FindBy(xpath="//input[@value='Add this task']")
	private WebElement saveTaskBtn;

	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	public void addTask() {
		clickOnProject.click();
		tasksBtn.click();
		addTasksBtn.click();
	}
	
	public void enterYourTask(String task) {
		addTaskText.sendKeys(task);
	}
	
	public void saveTask() {
		saveTaskBtn.click();
	}	
}
