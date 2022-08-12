package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utility.SeleniumDriver;
import utility.TestReporter;
import org.openqa.selenium.By;

public class LandingPage extends SeleniumDriver {
    SeleniumDriver driverActions = new SeleniumDriver();
    TestReporter testReporter = new TestReporter();


    By personalLoanButton=By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/om-wc-config/div/om-page/div/div/main/om-product-carousel/div/om-section/div/div/div[2]/div/om-carousel-container/div/div[1]/ul/li[2]/om-refined-product-card/om-product-card/div/div[2]/div[2]/div[2]/om-button");


    public void assertHeadingAndNavigateToPersonalLoanPage() throws InterruptedException {
        String title=driver.getTitle();
        Assert.assertEquals(title,"Bank and Borrow Solutions | Old Mutual");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scroll(0, 800);");
        Thread.sleep(2000);


        driverActions.click(personalLoanButton);
        Thread.sleep(2000);
        TestReporter.LogStep("Clicked Personal Loan Button");
    }
}
