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
public class SuppliersPage {
	//define Repository
	WebDriver driver;
	@FindBy(xpath 		= "(//a[text()='Suppliers'])[2]") WebElement clickSuppliers;
	@FindBy(xpath 		= "(//a[@data-caption='Add'])[1]") WebElement clickAddBtn;
	@FindBy(name		= "x_Supplier_Number") WebElement supplierNumber;
	@FindBy(name		= "x_Supplier_Name") WebElement suppliername;
	@FindBy(name		= "x_Address") WebElement supplierAddress;
	@FindBy(name		= "x_City") WebElement supplierCity;
	@FindBy(name		= "x_Country") WebElement supplierCountry;
	@FindBy(name		= "x_Contact_Person") WebElement supplierContactPerson;
	@FindBy(name		= "x_Phone_Number") WebElement supplierPhoneNumber;
	@FindBy(name		= "x__Email") WebElement supplierEmail;
	@FindBy(name		= "x_Mobile_Number") WebElement supplierMobileNumber;
	@FindBy(name		= "x_Notes") WebElement supplierNotes;
	@FindBy(id			= "btnAction") WebElement supplierAddbtn;
	@FindBy(xpath 		= "//button[text()='OK!']") WebElement clickConfirmBtn;
	@FindBy(xpath 		= "(//button[text()='OK'])[6]") WebElement clickAlertOk;
	@FindBy(xpath 		= "//button[@data-caption='Search Panel']") WebElement clickSearchPanel;
	@FindBy(xpath 		= "//input[@id='psearch']") WebElement EnterserachTextbox;
	@FindBy(xpath 		= "//button[@id='btnsubmit']") WebElement clickSearchbutton;
	@FindBy(id 			= "tbl_a_supplierslist") WebElement webTable;

	 public SuppliersPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
//write boolean method for supplier 
	 public boolean Add_Supplier(String supplierName,String Address,String City,
	 String Country,String cperson,String Pnumber,String email,String mNumber,String Notes) throws Throwable
	 {
		 WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 mywait.until(ExpectedConditions.elementToBeClickable(this.clickSuppliers));
		 this.clickSuppliers.click();
		 mywait.until(ExpectedConditions.elementToBeClickable(this.clickAddBtn));
		 this.clickAddBtn.click();
		 mywait.until(ExpectedConditions.visibilityOf(this.supplierNumber));
		 //capture supplier number
		 String Expected_Sup= this.supplierNumber.getAttribute("value");
		 this.suppliername.sendKeys(supplierName);
		 this.supplierAddress.sendKeys(Address);
		 this.supplierCity.sendKeys(City);
		 this.supplierCountry.sendKeys(Country);
		 this.supplierContactPerson.sendKeys(cperson);
		 this.supplierPhoneNumber.sendKeys(Pnumber);
		 this.supplierEmail.sendKeys(email);
		 this.supplierMobileNumber.sendKeys(mNumber);
		 this.supplierNotes.sendKeys(Notes);
		 this.supplierAddbtn.sendKeys(Keys.ENTER);
		 mywait.until(ExpectedConditions.elementToBeClickable(this.clickConfirmBtn));
		 this.clickConfirmBtn.click();
		 mywait.until(ExpectedConditions.elementToBeClickable(this.clickAlertOk));
		 this.clickAlertOk.click();
		 if(!this.EnterserachTextbox.isDisplayed())
		 mywait.until(ExpectedConditions.elementToBeClickable(this.clickSearchPanel));
		 this.clickSearchPanel.click();
		 mywait.until(ExpectedConditions.visibilityOf(this.EnterserachTextbox));
		 this.EnterserachTextbox.clear();
		 this.EnterserachTextbox.sendKeys(Expected_Sup);
		 this.clickSearchbutton.click();
		 Thread.sleep(2000);
		 String Actual_Sup = driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")).getText();
		if(Actual_Sup.equals(Expected_Sup))
		{
			return true;
		}
		else
		{
			return false;
		}
		}
}
