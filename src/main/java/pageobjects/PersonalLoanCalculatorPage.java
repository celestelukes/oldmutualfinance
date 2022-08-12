package pageobjects;

import utility.SeleniumDriver;
import utility.TestReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
public class PersonalLoanCalculatorPage {

    SeleniumDriver driverActions = new SeleniumDriver();
    TestReporter testReporter = new TestReporter();

    By personalLoanalculatorHeading=By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/om-wc-config/div/om-page/div/div/header/om-header-with-breadcrumbs/div/om-hero-banner/div/div[3]/div[2]/om-hero-banner-content/span/div/span/span");
    By howMuchDoYouNeedDropDown=By.xpath("//*[@id=\"loanAmount\"]/div/div/div/om-form-dropdown-field");
    By amount=By.xpath("//*[@id=\"R50000\"]");
    By nextButton=By.xpath("//*[@id=\"undefined\"]/button");
    By repaymentTermDropDown=By.xpath("//*[@id=\"repaymentDuration\"]/div/div/div/om-form-dropdown-field/div");
    By repaymentMonth=By.xpath("//*[@id=\"60 Months\"]");
    By calculateButton=By.xpath("//*[@id=\"undefined\"]/button/span[1]");
    By repaymentAmount=By.xpath("//*[@id=\"blt9c764616951e6d18\"]/div/div[2]/div[2]/om-calculator-result/div[2]/h5/strong");

}
