package org.jsystemtest.teamworktests;

import java.util.concurrent.TimeUnit;

import jsystem.framework.ParameterProperties;
import jsystem.framework.TestProperties;
import jsystem.framework.scenario.UseProvider;
import junit.framework.SystemTestCase4;

import org.jsystemtest.teamworkso.DashboardPage;
import org.jsystemtest.teamworkso.LoginPage;
import org.jsystemtest.teamworkso.Web;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreateTasksTests extends SystemTestCase4 {
	
	private Web web;
	private TeamworkpmAccount teamworkpmAccount;


	@Before
	public void getResources() throws Exception {
		web = (Web) system.getSystemObject("Web");
		web.setImplicitWait(10, TimeUnit.SECONDS);
	}
	
	/**JAVADOC examples
	 * 
	 * given - What's the initial state of the test (system)  
	 * when - What are we testing
	 * then - What is the expected result
	 * 
	 *  e.g:
	 *  GIVEN - Their is an existing account and project
	 *  WHEN - Adding task to the specific selected project. 
	 *  THEN - The task showing on the screen and added successfully
	 *  
	 *  Numbers
	 *  
	 *  1) Enter the username and password and login
	 *  2) Click on the your projact.
	 *  3) Click on the Tasks tab.
	 *  4) Click on the Add task button.
	 *  5) Enter the task text.
	 *  6) Click on the add task button.
	 *  
	 */
	
	@Test
	@TestProperties(name = "Add new task", paramsInclude = { "teamworkpmAccount" })
	public void addTaskTest()  {
		
		report.step("GIVEN - Their is an existing account and project");
		LoginPage loginPage = web.getLoginPage();
		report.report("Setting the username");
		report.report("You forgot to enter the parameter username");
		loginPage.setUserName(teamworkpmAccount.getUserName());
		report.report("Setting the password");
		report.report("You forgot to enter the parameter password");
		loginPage.setPassword(teamworkpmAccount.getPassword());
		report.report("Clicking on login button");
		DashboardPage dashboardPage = loginPage.login();
		
		report.step("WHEN - Adding task to the specific selected project.");
		dashboardPage.addTask();
		String taskTitle = String.valueOf(System.currentTimeMillis());
		dashboardPage.enterYourTask(taskTitle);
		dashboardPage.saveTask();
		
		report.step("THEN - The task showing on the screen and added successfully");
		Assert.assertEquals("could not find element with text " + taskTitle, true, web.isTextExistOnPage(taskTitle));
	}
	
	@After
	public void clearResources() {
		web.quit();
	}
	
	public TeamworkpmAccount getTeamworkpmAccount() {
		return teamworkpmAccount;
	}
	
	@ParameterProperties(description = "This param holds the login information to teamworkpm")
	@UseProvider(provider = jsystem.extensions.paramproviders.GenericObjectParameterProvider.class)
	public void setTeamworkpmAccount(TeamworkpmAccount teamworkpmAccount) {
		this.teamworkpmAccount = teamworkpmAccount;
	}
	

}
