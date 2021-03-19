package com.liidaveq.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

	private By nav_menu = By.cssSelector("a[href=\"#navigation\"]");
	private By sale_menu = By.linkText("Sales Tools");
	private By lead_menu = By.cssSelector("a[data-target=\"#select-lead-model\"]");
	private By lead_modal = By.id("select-lead-model");
	private By proposal_menu = By.linkText("SELECT LEAD");
	private By nav_user_menu = By.id("lp-dropdown-schedule");
	private By nav_user_logout = By.id("logout_id");

	private WebDriver driver;
	private WebDriverWait wait;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 30);
	}

	public void ClickLogout() {

		Actions browser_action = new Actions(driver);
		browser_action.moveByOffset(0, 0).perform();;
		wait.until(ExpectedConditions.visibilityOfElementLocated(nav_user_menu));
//		browser_action.moveToElement(driver.findElement(nav_user_menu)).perform();
//		browser_action.perform();
		driver.findElement(nav_user_menu).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(nav_user_logout));
		wait.until(ExpectedConditions.elementToBeClickable(nav_user_logout));
		driver.findElement(nav_user_logout).click();
	}

	public LeadPage NavigateToLeadPage() {
		// Click NavBar Menu
		wait.until(ExpectedConditions.visibilityOfElementLocated(nav_menu));
		driver.findElement(nav_menu).click();

		// Click Sales Tools Menu
		wait.until(ExpectedConditions.visibilityOfElementLocated(sale_menu));
		driver.findElement(sale_menu).click();

		// Click Leads Menu
		wait.until(ExpectedConditions.visibilityOfElementLocated(lead_menu));
		driver.findElement(lead_menu).click();

		// Click New Leads Button
		wait = new WebDriverWait(this.driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(lead_modal));
		wait.until(ExpectedConditions.visibilityOfElementLocated(proposal_menu));
		wait.until(ExpectedConditions.elementToBeClickable(proposal_menu));
		Actions browser_action = new Actions(driver);
//		driver.navigate().to("https://www.liidaveqa.com/proposal-tool/search/leads");
		browser_action.moveToElement(driver.findElement(proposal_menu)).click(driver.findElement(proposal_menu)).perform();
		return new LeadPage(driver);
	}

}
