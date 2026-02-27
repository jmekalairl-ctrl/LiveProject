package utils;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class BaseClass {
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("./target/reports/LoginTest.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Data Driven Testing");
        spark.config().setReportName("Test Execution Results");
        
    }

    @BeforeMethod
    public void setupBrowser(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String path = captureScreenshot(result.getName());
            test.fail("Failed: " + result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Passed");
        }
        if (driver != null) driver.quit();
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