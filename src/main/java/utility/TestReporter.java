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


    public static void addScreenShotToLog() throws Exception {

        String screenshotPath = driverActions.GetScreenshot();
        test.log(LogStatus.INFO,  test.addScreenCapture(screenshotPath));
    }

    public static void passScenario(String passInfo) throws Exception {

        test.log(LogStatus.PASS, passInfo);
        String screenshotPath = driverActions.GetScreenshot();
        test.log(LogStatus.PASS,  test.addScreenCapture(screenshotPath));
    }

}
