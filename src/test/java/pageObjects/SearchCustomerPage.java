package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {

	public WebDriver ldriver;
	WaitHelper waithelper;
	
	public SearchCustomerPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver,this);
		waithelper=new WaitHelper(ldriver);
		
	}
	
	@FindBy(id="SearchEmail") WebElement txtEmail;
	
	@FindBy(id="SearchFirstName") WebElement txtFirstName;
	
	@FindBy(id="SearchLastName") WebElement txtlastName;
	
	@FindBy(id="search-customers") WebElement btnSearch;
	
	@FindBy(xpath = "//table[@id='customers-grid']") WebElement table;
	
	@FindBy(xpath="//table[@id='customers-grid']//tbody/tr") List<WebElement> tableRows;
	
	@FindBy(xpath="//table[@id='customers-grid']//thead/tr/th") List<WebElement> tableColumns;
	
	
	public void setEmail(String email)
	{
		waithelper.waitForElement(txtEmail, 20);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setFirstName(String fname)
	{
		waithelper.waitForElement(txtFirstName, 20);
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		waithelper.waitForElement(txtlastName, 20);
		txtlastName.clear();
		txtlastName.sendKeys(lname);
	}
	
	public void clickSearch() {
		btnSearch.click();
		waithelper.waitForElement(btnSearch, 20);
		
	}
	
	public int getNoOfRows()
	{
		return(tableRows.size());
	}
	
	public int getNoOfcolumns()
	{
		return(tableColumns.size());
	}
	
	public boolean searchCustomerByEmail(String email)
	
	{
		boolean flag=false;
		
		for(int i=1;i<=getNoOfRows();i++)
		{
			String emailid=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();
			System.out.println(emailid);
			
			if(emailid.equals(email))
			{
				flag=true;
			}
			
			
		}
		
		return flag;
		
	}
	
	
public boolean searchCustomerByName(String email)
	
	{
		boolean flag=false;
		
		for(int i=1;i<=getNoOfRows();i++)
		{
			String name=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText();
			
			String names[]=name.split(" ");
			
			if(names[0].equals("Victoria") && names[1].equals("Terces"))
			{
				flag=true;
			}
			
			
		}
		
		return flag;
		
	}
}
