package utility;

import com.relevantcodes.extentreports.ExtentReports;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    public void SetupTest(String url, String testName) throws IOException {
        BrowserConfig();

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

    private void BrowserConfig() throws IOException {

        browser = p.getObjectRepository().getProperty("browser");

        if (browser.equals("Chrome")) {

            WebDriverManager.chromedriver().setup();

            if (driver == null) {
                driver = new ChromeDriver();
            }
        }
        else if (browser.equals("FireFox")){
            WebDriverManager.firefoxdriver().setup();
        }
        else if (browser.equals("Edge")) {
            WebDriverManager.edgedriver().setup();
        }
    }

    public void openPage(String url){
        driver.get(url);
    }

    public void TearDown(){



        if (driver!=null){

            driver.close();
            driver.quit();
        }
        TestReporter.extent.endTest(TestReporter.test);
        TestReporter.extent.flush();
    }

    //Actions Section
    public void Click(By locator) {

        WaitForElementToBeClickable(locator);
        driver.findElement(locator).click();
    }

    public void SelectDropDownItemUsingSelector(By locator, String optionText){

        WaitForElementToBeClickable(locator);
        Select dropDownOptions = new Select(driver.findElement(locator));
        dropDownOptions.selectByVisibleText(optionText);
    }

    public void SelectItemFromDropDown(By locator, String optionText){

        //string do build generic xpath
        String template = "//span[contains(text(),'%s')]";
        String dropDownItemXpath = String.format(template, optionText);
        WaitForElementToBeClickable(locator);
        //Click on the dropDown
        driver.findElement(locator).click();
        //Click on the item on the dropdown
        WaitForElementToBeClickable(By.xpath(dropDownItemXpath));
        driver.findElement(By.xpath(dropDownItemXpath)).click();
    }

    public void SelectItemFromSearchField(By locator, String optionText) throws InterruptedException {

        //string do build generic xpath
        String template = "//span[contains(text(),'%s')]";
        String dropDownItemXpath = String.format(template, optionText);
        WaitForElementToBeClickable(locator);
        //Clear search field

        driver.findElement(locator).clear();
        Thread.sleep(2000);
        driver.findElement(locator).sendKeys(optionText);
         String  isEmpty = driver.findElement(locator).getText();

        //Click on the item on the dropdown
        WaitForElementToBeClickable(By.xpath(dropDownItemXpath));
        driver.findElement(By.xpath(dropDownItemXpath)).click();
    }

    public void SendKeys(By locator, String text) {

        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public Boolean IsElementVisible(By locator)
    {
        boolean exists;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        exists = driver.findElements(locator).size() != 0;
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        return exists;

    }

    public List<WebElement> ElementList(By locator){

        return  driver.findElements(locator);
    }

    public void WaitForElementToBeClickable(By locator)  {

        wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable (locator));
    }

    public void ScrollElementToView(By locator){

        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", locator);
    }

    public  void scroll(){
        js.executeScript("window.scrollBy(0,600)");
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

    public String GetText(By locator){

        return driver.findElement(locator).getText();
    }

    public void ScrollToBottom(By Bodylocator) {

        /* body = driver.findElement(Bodylocator);

        Actions scrollDown = new Actions(driver).
                .MoveToElement(body, body.Size.Width - 10, 15) // position mouse over scrollbar
                .ClickAndHold()
                .MoveByOffset(0, 50) // scroll down
                .Release()
                .Build();

        scrollDown.Perform();*/


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
