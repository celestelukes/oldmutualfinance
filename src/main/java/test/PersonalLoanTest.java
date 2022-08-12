package test;

import org.testng.annotations.AfterMethod;
import utility.SeleniumDriver;
import pageobjects.*;
import utility.ExcelFunctions;
import utility.ReadObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class PersonalLoanTest {

    SeleniumDriver seleniumDriver;
    PersonalLoanPage personalLoanPage;
    LandingPage landingPage;
    PersonalLoanCalculatorPage personalLoanCalculatorPage;



    ReadObject  readObject = new ReadObject();
    String testName;
    String url;





    @BeforeMethod
    public void PrepareTest() throws IOException {

        seleniumDriver = new SeleniumDriver();
        url = readObject.getObjectRepository().getProperty("oldMutualUrl");
        testName = readObject.getObjectRepository().getProperty("personalLoanCalculatorFlow");
        seleniumDriver.setupTest(url, "Personal Loan Calculator Test");
        landingPage = new LandingPage();
        personalLoanPage=new PersonalLoanPage();
        personalLoanCalculatorPage= new PersonalLoanCalculatorPage();
    }

    @Test
    public void personalLoanCalculatorTest() throws Exception {
            landingPage.assertHeadingAndNavigateToPersonalLoanPage();
            personalLoanPage.clickCalculateButton();
            personalLoanCalculatorPage.switchToNewTabAndScrollToDropDownSelector();
            personalLoanCalculatorPage.selectLoanAmountDurationAndCalculate();
    }


    @AfterMethod
    public void CleanTest(){

        seleniumDriver.tearDown();
    }

}
