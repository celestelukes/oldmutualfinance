package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import utility.SeleniumDriver;
import utility.TestReporter;
import org.openqa.selenium.By;

import static utility.SeleniumDriver.driver;

public class PersonalLoanCalculatorPage {

    SeleniumDriver driverActions = new SeleniumDriver();

    By howMuchDoYouNeedDropDown=By.xpath("//*[@id=\"loanAmount\"]/div/div/div/om-form-dropdown-field/div/div[1]/span[1]");
    By amount=By.xpath("//*[@id=\"R50000\"]");
    By nextButton=By.xpath("//*[@id=\"undefined\"]/button");
    By repaymentDurationDropDown=By.xpath("//*[@id=\"repaymentDuration\"]/div/div/div/om-form-dropdown-field/div/div[1]/span[1]");
    By repaymentOption=By.xpath("//*[@id=\"60 Months\"]");
    By calculateLoanButton=By.xpath("(//span[normalize-space(text())='Calculate'])[1]");
    By monthlyRepayment=By.xpath("//*[@id=\"blt9c764616951e6d18\"]/div/div[2]/div[2]/om-calculator-result/div[2]/h5");

    public void switchToNewTabAndScrollToDropDownSelector() throws InterruptedException {
        Thread.sleep(2000);
        driverActions.SwitchToActiveWindow();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scroll(0, 800);");
    }


    public void selectLoanAmountDurationAndCalculate() throws Exception {
        Thread.sleep(500);
        driverActions.click(howMuchDoYouNeedDropDown);
        driverActions.click(amount);


        driverActions.click(nextButton);


        driverActions.click(repaymentDurationDropDown);
        driverActions.click(repaymentOption);



        driverActions.click(calculateLoanButton);


        Assert.assertEquals(driver.findElement(monthlyRepayment).getText(),"R1 656.43 - R1 810.05");
        TestReporter.passScenario("Successfully Calculated Loan Amount");
    }
}
