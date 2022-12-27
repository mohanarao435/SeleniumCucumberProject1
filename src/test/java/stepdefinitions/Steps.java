package stepdefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	
	@Before
	public void setup() throws IOException
	{
		configProp=new Properties();
		FileInputStream configPropfile=new FileInputStream("config.properties");
		configProp.load(configPropfile);
		
		
		String br=configProp.getProperty("browser");
		
		if(br.equals("chrome"))
		{
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		}
		
		else if(br.equals("firefox"))
		{
		
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		}
		
		else if(br.equals("edge"))
		{
		
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		}
		
		logger=Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure("log4j.properties");
		
		logger.info("*******Launching browser*******");
	}

	@Given("User Launch Chrome Browser")
	public void user_launch_chrome_browser() {
		
		
		lp=new LoginPage(driver);
	   
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("*******Opening URL*******");
	    driver.get(url);
	    driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("*******Providing Login details*******");
	    lp.setEmail(email);
	    lp.setPassword(password);
	}

	@When("Click on login")
	public void click_on_login() throws InterruptedException {
		logger.info("*******Started login*******");
	    lp.clickOnLogin();
	    Thread.sleep(3000);
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {
	    if(driver.getPageSource().contains("Login was unsuccessful")) {
	    	driver.close();
	    	logger.info("*******Login Failed*******");
	    	Assert.assertTrue(false);
	    }
	    else {
	    	
	    	logger.info("*******Login Passed*******");
	    	Assert.assertEquals(title, driver.getTitle());
	    }
	    
	}

	@When("User click on logout link")
	public void user_click_on_logout_link() throws InterruptedException {
		logger.info("*******Click on logout link*******");
	   lp.clickOnLogout();
	   Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
		logger.info("*******Closing browser*******");
	    driver.quit();
	}
	
	//Customer feature step definitions...
	
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
	    addCust=new AddCustomerPage(driver);
	    Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}
	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
	    Thread.sleep(3000);
		addCust.clickOnCustomersMenu();
	}
	@When("click on customers menu item")
	public void click_on_customers_menu_item() throws InterruptedException {
		 Thread.sleep(2000);
		 addCust.clickOnCustomersMenuItem();
	}
	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		addCust.clickOnAddNew();
	    Thread.sleep(2000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page()  {
	    Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}
	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		
		logger.info("*******Adding new Customer*******");
		logger.info("*******Providing Customer details*******");
	    String email=randomString()+"@gmail.com";
	    addCust.setEmail(email);
	    addCust.setPassword("test123");
	    
	    addCust.setCustomerRoles("Guests");
	    Thread.sleep(3000);
	    
	    addCust.setManagerOfvendor("Vendor 2");
	    addCust.setGender("Male");
	    addCust.setFirstName("David");
	    addCust.setLastName("Warner");
	    addCust.setDob("7/05/1998");
	    addCust.setCompanyName("busyQA");
	    addCust.setAdminContent("This is for Testing.....");
	}
	@When("click on save button")
	public void click_on_save_button() throws InterruptedException {
	   logger.info("*******Saving Customer data******");
	   addCust.clickOnSave();
	   Thread.sleep(3000);
	}
	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
	   Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains(msg));
	}
	
	//Steps for searching a customer by using Email Id ....
	@When("Enter Customer Email")
	public void enter_customer_email() {
		logger.info("*******Searching customer by email id*******");
	    searchCust=new SearchCustomerPage(driver);
	    searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}
	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
	    searchCust.clickSearch();
	    Thread.sleep(3000);
	}
	@Then("User should found Email in Search table")
	public void user_should_found_email_in_search_table() {
		boolean status=searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		
		Assert.assertEquals(true, status);
	    
	}
	
	//steps for searching customer by using first name & last name
	
	@When("Enter Customer FirstName")
	public void enter_customer_first_name() {
		logger.info("*******Searching customer by name*******");
	    searchCust=new SearchCustomerPage(driver);
	    searchCust.setFirstName("Victoria");
	}
	@When("Enter Customer LastName")
	public void enter_customer_last_name() {
		searchCust.setLastName("Terces");
	    
	}
	@Then("User should found Name in Search table")
	public void user_should_found_name_in_search_table() {
	   boolean status=searchCust.searchCustomerByName("Victoria Terces");
	   Assert.assertEquals(true,status);
	}



}
