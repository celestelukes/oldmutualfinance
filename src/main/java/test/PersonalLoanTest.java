package test;

import excelsheetfields.PersonalLoanCalculatorFields;
import org.testng.annotations.AfterMethod;
import utility.SeleniumDriver;
import pageobjects.*;
import utility.ExcelFunctions;
import utility.ReadObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
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
    String filePath;
    FileInputStream input_document;
    ExcelFunctions excel;
    String scenario;
    String loanAmount;
    String duration;
    String totalRepayment;



    @BeforeMethod
    public void PrepareTest() throws IOException {

        seleniumDriver = new SeleniumDriver();
        url = readObject.getObjectRepository().getProperty("oldMutualUrl");
        testName = readObject.getObjectRepository().getProperty("personalLoanCalculatorFlow");
        seleniumDriver.setupTest(url, "Personal Loan Calculator Test");
        filePath = readObject.getObjectRepository().getProperty("personalLoanCalculatorSheetPath");
        input_document = new FileInputStream(String.valueOf(new File(filePath)));
        excel = new ExcelFunctions(input_document);
        landingPage = new LandingPage();
        personalLoanPage=new PersonalLoanPage();
        personalLoanCalculatorPage= new PersonalLoanCalculatorPage();
    }

    @Test
    public void personalLoanCalculatorTest() throws Exception {

        for (int i = 1 ; i<ExcelFunctions.ScenarioCount; i++) {

            setData(i);
            landingPage.assertHeadingAndNavigateToPersonalLoanPage();
            personalLoanPage.clickCalculateButton();
            personalLoanCalculatorPage.switchToNewTabAndScrollToDropDownSelector();
            personalLoanCalculatorPage.selectLoanAmountDurationAndCalculate(loanAmount,duration, totalRepayment);

        }
    }


    @AfterMethod
    public void cleanTest(){

        seleniumDriver.tearDown();
    }

    private void setData(int Scenario) throws IOException {

        scenario = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanCalculatorFields.scenario));
        loanAmount = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanCalculatorFields.loanAmount));
        duration = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanCalculatorFields.duration));
        totalRepayment = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanCalculatorFields.totalRepayment));
    }
}
