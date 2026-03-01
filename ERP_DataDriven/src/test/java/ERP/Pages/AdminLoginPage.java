package ERP.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPage {
	WebDriver driver;

	@FindBy(id = "username") 	WebElement txtUsername;
	@FindBy(id = "password") 	WebElement txtPassword;
	@FindBy(id = "btnsubmit") 	WebElement btnLogin;
	@FindBy(id = "btnreset") 	WebElement btnReset;

	public AdminLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void login(String user, String pass) throws Throwable {
		txtUsername.clear();
		txtUsername.sendKeys(user);
		Thread.sleep(2000);
		txtPassword.clear();
		txtPassword.sendKeys(pass);
		Thread.sleep(2000);
		btnLogin.click();
	}
}

