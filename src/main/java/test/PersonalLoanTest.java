package test;

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


    String filePath;
    FileInputStream input_document;
    ExcelFunctions excel;
    ReadObject  readObject = new ReadObject();
    String testName;
    String url;
    String scenario;




    @BeforeMethod
    public void PrepareTest() throws IOException {

        seleniumDriver = new SeleniumDriver();
        url = readObject.getObjectRepository().getProperty("oldMutualUrl");
        testName = readObject.getObjectRepository().getProperty("personalLoanCalculatorFlow");
       // input_document = new FileInputStream(String.valueOf(new File(filePath)));
       // excel = new ExcelFunctions(input_document);
        seleniumDriver.SetupTest(url, "Personal Loan Calculator Test");
        landingPage = new LandingPage();
        personalLoanPage=new PersonalLoanPage();
    }

    @Test
    public void personalLoanCalculatorTest() throws Exception {


            landingPage.assertHeadingAndNavigateToPersonalLoanPage();
            personalLoanPage.clickCalculatorButton();

    }

}
