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


    By landingPageHeading =By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/om-wc-config/div/om-page/div/div/main/om-product-carousel/div/om-section/div/div/div[1]/om-section-header/div/div/div[2]/span/h1");
    By personalLoanButton=By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/om-wc-config/div/om-page/div/div/main/om-product-carousel/div/om-section/div/div/div[2]/div/om-carousel-container/div/div[1]/ul/li[2]/om-refined-product-card/om-product-card/div/div[2]/div[2]/div[2]/om-button");
    By calculatorButton=By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/om-wc-config/div/om-page/div/div/header/om-header-with-breadcrumbs/div/om-hero-banner/div/div[3]/div[2]/om-hero-banner-content/span/om-button[2]");
    By dropXpath=By.xpath("//*[@id=\"loanAmount\"]/div/div/div/om-form-dropdown-field/div/div[1]/span[1]");

    public void assertHeadingAndNavigateToPersonalLoanPage() throws InterruptedException {
        //System.out.println("Page title of new tab: " + driver.getTitle());
        String title=driver.getTitle();
        Assert.assertEquals(title,"Bank and Borrow Solutions | Old Mutual");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scroll(0, 800);");
        Thread.sleep(2000);
        driver.findElement(personalLoanButton).click();
        Thread.sleep(2000);
       /* driver.findElement(calculatorButton).click();
        Thread.sleep(4000);
        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        js1.executeScript("scroll(0, 1500);");
        Thread.sleep(2000);
        driver.findElement(dropXpath).click();*/

    }
}
