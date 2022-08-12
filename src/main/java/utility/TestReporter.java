package utility;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestReporter {

    //TestReporter members are static and must be implimented as such

    public static ExtentTest test;
    public static ExtentReports extent;
    static SeleniumDriver driverActions = new SeleniumDriver();
    public static void LogStep(String stepInfo){

        test.log(LogStatus.INFO, stepInfo);
    }

    public static void LogFailure(String reasonForFailure){

        test.log(LogStatus.FAIL, reasonForFailure);
    }

    public static void AddScreenShotToLog() throws Exception {

        String screenshotPath = driverActions.GetScreenshot();
        test.log(LogStatus.INFO,  test.addScreenCapture(screenshotPath));
    }

    public static void PassScenario(String passInfo) throws Exception {

        test.log(LogStatus.PASS, passInfo);
        String screenshotPath = driverActions.GetScreenshot();
        test.log(LogStatus.PASS,  test.addScreenCapture(screenshotPath));
    }

    public static void FailScenario(String reasonForFailure) throws Exception {

        test.log(LogStatus.FAIL, reasonForFailure);
        String screenshotPath = driverActions.GetScreenshot();
        test.log(LogStatus.FAIL,  test.addScreenCapture(screenshotPath));
    }



}
