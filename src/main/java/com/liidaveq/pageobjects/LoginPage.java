package com.liidaveq.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private By signin_btn = By.cssSelector("a.login-link.btn"); //Anchor
	private By login_form = By.id("loginForm"); //form
	private By user_id = By.id("j_username"); 	//input
	private By user_pwd = By.id("j_password");	//input
	private By login_submit = By.id("loginSubmit"); // Button
	
	private WebDriver driver;
	private WebDriverWait wait;
			
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 10);
	}
	
	public void NavigateToSignInPage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((signin_btn)));
		driver.findElement(signin_btn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated((login_form)));
	}
	
	public void FillLoginForm(String user_name, String password) {
		driver.findElement(user_id).sendKeys(user_name);
		driver.findElement(user_pwd).sendKeys(password);
	}
	
	public DashboardPage ClickLoginButton() {
		driver.findElement(login_submit).click();
		return new DashboardPage(driver);
	}

}
