package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utility.SeleniumDriver;
import utility.TestReporter;
import org.openqa.selenium.By;

import java.util.ArrayList;

public class PersonalLoanPage extends SeleniumDriver {
    SeleniumDriver driverActions = new SeleniumDriver();
    TestReporter testReporter = new TestReporter();

    By calculateButton=By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/om-wc-config/div/om-page/div/div/header/om-header-with-breadcrumbs/div/om-hero-banner/div/div[3]/div[2]/om-hero-banner-content/span/om-button[2]");
    By dropXpath=By.xpath("//*[@id=\"loanAmount\"]/div/div/div/om-form-dropdown-field");
    By dropXpath2=By.xpath("//*[@id=\"loanAmount\"]/div/div/div/om-form-dropdown-field/div/div[1]/span[1]");
    By dropXpath1=By.xpath("//*[@id=\"R50000\"]");
    By heading=By.xpath("//*[@id=\"calculator_container\"]/div/om-1-col-layout/div/om-section/div/div/div[1]/om-section-header/div/div/div[1]/span/span");

    By nextButton=By.xpath("//*[@id=\"undefined\"]/button");
    By repaymentDurationDropDown=By.xpath("//*[@id=\"repaymentDuration\"]/div/div/div/om-form-dropdown-field/div/div[1]/span[1]");
    By repaymentOption=By.xpath("//*[@id=\"60 Months\"]");
    By calculateLoanButton=By.xpath("(//span[normalize-space(text())='Calculate'])[1]");
    //*[@id="undefined"]/button/span[1]
    //*[@id="undefined"]/button/span[1]
    By monthlyRepayment=By.xpath("//*[@id=\"blt9c764616951e6d18\"]/div/div[2]/div[2]/om-calculator-result/div[2]/h5");



    public void clickCalculatorButton() throws InterruptedException {

        Thread.sleep(2000);
        driver.findElement(calculateButton).click();
        Thread.sleep(2000);


        Thread.sleep(1000);
        // hold all window handles in array list
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        //switch to new tab
        driver.switchTo().window(newTb.get(1));
        System.out.println("Page title of new tab: " + driver.getTitle());
        //switch to parent window
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scroll(0, 800);");
       /* driver.switchTo().window(newTb.get(0));
        System.out.println("Page title of parent window: " + driver.getTitle());*/



        Thread.sleep(2000);
        //Assert.assertEquals(driver.findElement(heading).getText(),"Our Bank and Borrow solutions");
        driver.findElement(dropXpath2).click();
        driver.findElement(dropXpath1).click();

        driver.findElement(nextButton).click();


        driver.findElement(repaymentDurationDropDown).click();
        driver.findElement(repaymentOption).click();


        driver.findElement(calculateLoanButton).click();

        Assert.assertEquals(driver.findElement(monthlyRepayment).getText(),"R1 656.43 - R1 810.05");

    }
}
