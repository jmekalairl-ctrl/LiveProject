package ERP.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLogoutPage {
	WebDriver driver;
	public AdminLogoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath ="(//a[starts-with(text(),' Logout')])[2]" ) WebElement LogoutLink;
	public void adminLogout()
	{
		this.LogoutLink.click();
	}
}