package utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import ERP.Pages.AdminLoginPage;
import ERP.Pages.AdminLogoutPage;
import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseClass {
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
   public static Properties conpro;
    @BeforeSuite
    public void setUpReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("./target/reports/ERPTest.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Data Driven Testing");
        spark.config().setReportName("Test Execution Results");
        
    }

    @BeforeMethod
    public void setupBrowser(ITestResult result)throws Throwable {
        test = extent.createTest(result.getMethod().getMethodName());
       conpro = new Properties();
       conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
       if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
       {
    	   driver = new ChromeDriver();
    	   driver.manage().window().maximize();
    	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	   driver.get(conpro.getProperty("Url"));
    	   AdminLoginPage loginpage = new AdminLoginPage(driver);
    	   loginpage.login("admin", "master");
       }
       else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
       {
    	  driver = new FirefoxDriver();
    	   driver.manage().window().maximize();
    	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	   driver.get(conpro.getProperty("Url"));
    	   AdminLoginPage loginpage = new AdminLoginPage(driver);
    	   loginpage.login("admin", "master");
       }
       else
       {
    	  try {
			throw new IllegalArgumentException("Browser value is Not matching");
		} catch (IllegalArgumentException e) {
			Reporter.log(e.getMessage(),true);
		} 
       }
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String path = captureScreenshot(result.getName());
            test.fail("Failed: " + result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Passed");
        }
        
        AdminLogoutPage logout = new AdminLogoutPage(driver);
        logout.adminLogout();
        driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush(); // Writes data
        
    }

    public String captureScreenshot(String name) throws IOException {
        String path = System.getProperty("user.dir") + "./target/reports/screenshots/" + name + ".png";
        FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File(path));
        return path;
    }
}