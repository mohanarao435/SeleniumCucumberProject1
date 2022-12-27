package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

	public WebDriver ldriver;
	
	public AddCustomerPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver,this);
	}
	
	By lnkCustomers_menu=By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By lnkCustomers_menuitem=By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
	By btnAddnew=By.xpath("//a[normalize-space()='Add new']");
	By txtEmail=By.xpath("//input[@id='Email']");
	By txtPassword=By.xpath("//input[@id='Password']");
	By txtFirstname=By.xpath("//*[@id='FirstName']");
	By txtLastname=By.xpath("//*[@id='LastName']");
	
	By txtcustomerRoles=By.xpath("//div[@class='input-group-append input-group-required']//div[@role='listbox']");
	
	By lstitemAdministrators=By.xpath("//li[contains(text(),'Administrators')]");
	By lstitemForumModerators=By.xpath("//li[contains(text(),'Forum Moderators')]");
	By lstitemGuests=By.xpath("//li[contains(text(),'Guests')]");
	By lstitemVendors=By.xpath("//li[contains(text(),'Vendors')]");
	By lstitemRegistered=By.xpath("/li[contains(text(),'Registered')]");
	
	By drpmgrmgrOfVendor=By.xpath("//*[@id='VendorId']");
	
	By rdMaleGender=By.id("Gender_Male");
	By rdFemaleGender=By.id("Gender_Female");
	
	By txtDob=By.xpath("//*[@id='DateOfBirth']");
	By txtCompanyname=By.xpath("//*[@id='Company']");
	By txtAdminContent=By.xpath("//*[@id='AdminComment']");
	By btnSave=By.xpath("//*[@name='save']");
	
	//Action Methods
	
	public String getPageTitle()
	{
		return ldriver.getTitle();
	}
	
	public void clickOnCustomersMenu() {
		ldriver.findElement(lnkCustomers_menu).click();
	}
	
	public void clickOnCustomersMenuItem() {
		ldriver.findElement(lnkCustomers_menuitem).click();
	}
	
	public void clickOnAddNew() {
		ldriver.findElement(btnAddnew).click();
	}
	
	public void setEmail(String email) {
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String pwd) {
		ldriver.findElement(txtPassword).sendKeys(pwd);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException 
	{
		if(!role.equals("Vendors"))
		{
			ldriver.findElement(By.xpath("//*[@id='SelectedCustomerRoleIds_taglist']/li/span[@title='delete']")).click();
		}
		
		ldriver.findElement(txtcustomerRoles).click();
		
		WebElement listitem;
		
		Thread.sleep(3000);
		
		if(role.equals("Administrators"))
		{
			listitem=ldriver.findElement(lstitemAdministrators);
		}
		

		else if(role.equals("Forum Moderators"))
		{
			listitem=ldriver.findElement(lstitemForumModerators);
		}
		
		
		else if(role.equals("Registered"))
		{
			listitem=ldriver.findElement(lstitemRegistered);
		}
		
		else if(role.equals("Vendors"))
		{
			listitem=ldriver.findElement(lstitemVendors);
		}
		else 
		{
			listitem=ldriver.findElement(lstitemGuests);
		}
		
		listitem.click();
		
		//JavascriptExecutor js=(JavascriptExecutor)ldriver;
		//js.executeScript("args[0].click();", listitem);
		
	}
	
	public void setManagerOfvendor(String value)
	{
		Select drp=new Select(ldriver.findElement(drpmgrmgrOfVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender)
	{
		if(gender.equals("Male"))
		{
			ldriver.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female"))
		{
			ldriver.findElement(rdFemaleGender).click();
		}
		else
		{
			ldriver.findElement(rdMaleGender).click(); //default
		}
			
	}
	
	public void setFirstName(String fname)
	{
		ldriver.findElement(txtFirstname).sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		ldriver.findElement(txtLastname).sendKeys(lname);
	}
	
	public void setDob(String dob)
	{
		ldriver.findElement(txtDob).sendKeys(dob);
	}
	
	
	public void setCompanyName(String comname)
	{
		ldriver.findElement(txtCompanyname).sendKeys(comname);
	}
	
	
	public void setAdminContent(String content)
	{
		ldriver.findElement(txtAdminContent).sendKeys(content);
	}
	
	public void clickOnSave() {
		ldriver.findElement(btnSave).click();
	}
	
}
