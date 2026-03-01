package ERP.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomersPage {
	//define Repository
		WebDriver driver;
		@FindBy(xpath 	= "(//a[text()='Suppliers'])[2]") 	WebElement clickSuppliers;
		@FindBy(xpath 	= "(//a[@data-caption='Add'])[1]") 	WebElement clickAddBtn;
		@FindBy(name	= "x_Supplier_Number") 				WebElement customerNumber;
		@FindBy(name	= "x_Supplier_Name") 				WebElement customername;
		@FindBy(name	= "x_Address") 						WebElement customerAddress;
		@FindBy(name	= "x_City") 						WebElement customerCity;
		@FindBy(name	= "x_Country") 						WebElement customerCountry;
		@FindBy(name	= "x_Contact_Person") 				WebElement customerContactPerson;
		@FindBy(name	= "x_Phone_Number") 				WebElement customerPhoneNumber;
		@FindBy(name	= "x__Email") 						WebElement customerEmail;
		@FindBy(name	= "x_Mobile_Number") 				WebElement customerMobileNumber;
		@FindBy(name	= "x_Notes") 						WebElement customerNotes;
		@FindBy(id		= "btnAction") 						WebElement customerAddbtn;
		@FindBy(xpath 	= "//button[text()='OK!']") 		WebElement clickConfirmBtn;
		@FindBy(xpath 	= "(//button[text()='OK'])[6]") 	WebElement clickAlertOk;
		@FindBy(xpath 	= "//button[@data-caption='Search Panel']") WebElement clickSearchPanel;
		@FindBy(xpath 	= "//input[@id='psearch']") 		WebElement EnterserachTextbox;
		@FindBy(xpath 	= "//button[@id='btnsubmit']") 		WebElement clickSearchbutton;
		@FindBy(id 		= "tbl_a_customerslist") 			WebElement webTable;

		 public CustomersPage(WebDriver driver) {
		        this.driver = driver;
		        PageFactory.initElements(driver, this);
		    }
	//write boolean method for supplier 
		 public boolean Add_Customer(String customerName,String Address,String City,
		 String Country,String cperson,String Pnumber,String email,String mNumber,String Notes)
		 {
			 WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
			 mywait.until(ExpectedConditions.elementToBeClickable(this.clickSuppliers));
			 this.clickSuppliers.click();
			 mywait.until(ExpectedConditions.elementToBeClickable(this.clickAddBtn));
			 this.clickAddBtn.click();
			 mywait.until(ExpectedConditions.visibilityOf(this.customerNumber));
			 //capture supplier number
			 String Expected_cus= this.customerNumber.getAttribute("value");
			 this.customername.sendKeys(customerName);
			 this.customerAddress.sendKeys(Address);
			 this.customerCity.sendKeys(City);
			 this.customerCountry.sendKeys(Country);
			 this.customerContactPerson.sendKeys(cperson);
			 this.customerPhoneNumber.sendKeys(Pnumber);
			 this.customerEmail.sendKeys(email);
			 this.customerMobileNumber.sendKeys(mNumber);
			 this.customerNotes.sendKeys(Notes);
			 this.customerAddbtn.sendKeys(Keys.ENTER);
			 mywait.until(ExpectedConditions.elementToBeClickable(this.clickConfirmBtn));
			 this.clickConfirmBtn.click();
			 mywait.until(ExpectedConditions.elementToBeClickable(this.clickAlertOk));
			 this.clickAlertOk.click();
			  if(!this.EnterserachTextbox.isDisplayed())
				 mywait.until(ExpectedConditions.elementToBeClickable(this.clickSearchPanel));
			 this.clickSearchPanel.click();
			 mywait.until(ExpectedConditions.visibilityOf(this.EnterserachTextbox));
			 this.EnterserachTextbox.clear();
			 this.EnterserachTextbox.sendKeys(Expected_cus);
			 this.clickSearchbutton.click();
			 mywait.until(ExpectedConditions.visibilityOf(this.webTable));
			 String Actual_cus = driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")).getText();
			if(Actual_cus.equals(Expected_cus))
			{
				return true;
			}
			else
			{
				return false;
			}
  }
}