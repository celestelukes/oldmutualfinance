package pageobjects;

import utility.SeleniumDriver;
import utility.TestReporter;
import org.openqa.selenium.By;

public class PersonalLoanPage extends SeleniumDriver {
    SeleniumDriver driverActions = new SeleniumDriver();
    TestReporter testReporter = new TestReporter();

    By calculateButton=By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/om-wc-config/div/om-page/div/div/header/om-header-with-breadcrumbs/div/om-hero-banner/div/div[3]/div[2]/om-hero-banner-content/span/om-button[2]");


    public void clickCalculateButton(){

        driverActions.click(calculateButton);
        TestReporter.LogStep("Clicked Calculator Button");

    }
}
