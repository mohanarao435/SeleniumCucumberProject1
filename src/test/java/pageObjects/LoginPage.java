package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
	}
	
	@FindBy(id="Email") WebElement email;
	
	@FindBy(id="Password") WebElement password;
	
	@FindBy(xpath ="//button[@type='submit']") WebElement loginBtn;
	
	@FindBy(linkText = "Logout") WebElement logoutBtn;
	
	public void setEmail(String eid)
	{
		email.clear();
		email.sendKeys(eid);
	}
	
	public void setPassword(String pwd)
	{
		password.clear();
		password.sendKeys(pwd);
	}
	
	public void clickOnLogin()
	{
		loginBtn.click();
	}
	
	public void clickOnLogout()
	{
		logoutBtn.click();
	}
	
}
