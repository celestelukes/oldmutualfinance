package pageobjects;

import org.testng.Assert;
import utility.SeleniumDriver;
import utility.TestReporter;
import org.openqa.selenium.By;

public class PersonalLoanPage extends SeleniumDriver {
    SeleniumDriver driverActions = new SeleniumDriver();

    By calculateButton=By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/om-wc-config/div/om-page/div/div/header/om-header-with-breadcrumbs/div/om-hero-banner/div/div[3]/div[2]/om-hero-banner-content/span/om-button[2]");
    By personalPageHeader=By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/om-wc-config/div/om-page/div/div/header/om-header-with-breadcrumbs/div/om-hero-banner/div/div[3]/div[2]/om-hero-banner-content/span/div/span/span");


    public void clickCalculateButton() throws Exception {

        Assert.assertEquals(driver.findElement(calculateButton).getText(),"Get the loan not the stress");

        driverActions.click(calculateButton);
        TestReporter.LogStep("Clicked Calculator Button");TestReporter.addScreenShotToLog();

    }
}
