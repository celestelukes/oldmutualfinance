package utility;

import com.relevantcodes.extentreports.ExtentReports;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class SeleniumDriver  {


    public SeleniumDriver(){

    }

    public static WebDriver driver;
    public static WebDriverWait webDriverWait;
    ReadObject  p = new ReadObject();

    public final static int TIMEOUT =120;
    public final static int PAGE_LOAD_TIMEOUT =120;
    public  static String browser;
    private WebDriverWait wait;
    JavascriptExecutor js;

    public void setupTest(String url, String testName) throws IOException {
        browserConfig();

       // ReportClass.ReportDirectory();
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver,TIMEOUT);
        driver.manage().timeouts().implicitlyWait(TIMEOUT,TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
        TestReporter.extent = new ExtentReports(ReportClass.fullReportPath, true);
        TestReporter.test = TestReporter.extent.startTest(testName);
        openPage(url);
    }

    private void browserConfig() throws IOException {

        browser = p.getObjectRepository().getProperty("browser");

        if (browser.equals("Chrome")) {

            WebDriverManager.chromedriver().setup();

            if (driver == null) {
                driver = new ChromeDriver();
            }
        }
        else if (browser.equals("FireFox")){
            WebDriverManager.firefoxdriver().setup();
            if (driver == null) {
                driver = new FirefoxDriver();
            }
        }
    }

    public void openPage(String url){
        driver.get(url);
    }

    public void tearDown(){



        if (driver!=null){

            driver.close();
            driver.quit();
        }
        TestReporter.extent.endTest(TestReporter.test);
        TestReporter.extent.flush();
    }

    //Actions Section
    public void click(By locator) {

        waitForElementToBeClickable(locator);
        driver.findElement(locator).click();
    }

    public void waitForElementToBeClickable(By locator)  {

        wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable (locator));
    }



    public void SwitchToActiveWindow(){

        String mainWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

    }


    public String GetScreenshot() throws Exception {
        //below line is just to append the date format with the screenshot name to avoid duplicate names
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = ReportClass.screenshortReportPath + File.separator + dateName+".PNG";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        //Returns the captured file path
        return destination;
    }

}
