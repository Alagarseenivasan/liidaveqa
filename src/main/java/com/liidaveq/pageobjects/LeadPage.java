package com.liidaveq.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeadPage {

	private By leads_heading = By.cssSelector("h1.leads-details-heading");
	private By btn_new_lead = By.cssSelector("a.introjs-l-9");

	private By first_name = By.id("firstName"); 				// Input
	private By last_name = By.id("lastName"); 					// Input
	private By phone_number = By.id("phNo"); 					// Input
	private By email = By.id("email"); 							// Input
	private By request_date = By.id("calender1"); 				//Input
	private By request_time = By.id("scheduleRequestTime");		//Select
	private By save_lead = By.id("btn-addLeadsForm"); 			// Button
	private By success_message = By.id("globalMessages"); 		// Div

	private WebDriver driver;
	private WebDriverWait wait;
	Actions browser_action;

	public LeadPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 50);
	}

	public void ClickNewLead() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(leads_heading));
		wait.until(ExpectedConditions.visibilityOfElementLocated(btn_new_lead));
		driver.findElement(btn_new_lead).click();
	}

	public void saveLeadProposal() {
		browser_action = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(save_lead));
		browser_action.moveToElement(driver.findElement(save_lead));
		driver.findElement(save_lead).click();
	}

	public void setFirstName(String data) {
		driver.findElement(first_name).sendKeys(data);
	}
	
	public void setLastName(String data) {
		driver.findElement(last_name).sendKeys(data);
	}
	
	public void setPhoneNumber(String data) {
		driver.findElement(phone_number).sendKeys(data);
	}
	
	public void setEmail(String data) {
		driver.findElement(email).sendKeys(data);
	}
	
	public void setSchedulingDate(String data) {
		browser_action = new Actions(driver);
		browser_action.moveToElement(driver.findElement(request_date)).click().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(request_date));
//		browser_action.perform();
		driver.findElement(request_date).sendKeys(data);
	}
	
	public void setSchedulingTime(String data) {
		
		browser_action = new Actions(driver);
		browser_action.moveToElement(driver.findElement(request_time)).click().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(request_time));
		Select scheduleTime = new Select(driver.findElement(request_time));
		scheduleTime.selectByValue(data);
	}	
}
