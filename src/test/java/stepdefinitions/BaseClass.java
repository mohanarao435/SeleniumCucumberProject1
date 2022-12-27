package stepdefinitions;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {

	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage addCust;
	public SearchCustomerPage searchCust;
	public Logger logger;
	
	public  Properties configProp;
	
	// random string for email
	public static String randomString() {
		String generateString=RandomStringUtils.randomAlphabetic(5);
		return generateString;
	}
}
