package utility;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class ReportClass {

    public  static String  fullReportPath ="Reports/PersonalLoanCalculator_Reports.html";
    public  static String  screenshortReportPath;


    public static void ReportDirectory() throws IOException {

        File rootReportFolder = new File( fullReportPath);

        //check if the folder doesn't already exist on the file system.
        //If the folder already exists, do nothing, else create the folder.
        if (!rootReportFolder.exists()) {
            LOGGER.info("creating directory: " + rootReportFolder);
            try {
                rootReportFolder.mkdir();
            } catch (Exception e) {
                LOGGER.info("Failed to create directory" + rootReportFolder);
            }
        }

        // Root Test Suite Folder [Example : Home Loans.]
        File testFolder = new File( rootReportFolder + File.separator);
        if (!testFolder.exists()) {
            LOGGER.info("Creating Site directory" + testFolder);
            testFolder.mkdir();
        }

        //Root Test Report Folder  [Example : Search HomeLoans Application Test.]
        //HTML test report folder
        String reportFolderName = "Automated Test Execution Report_" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        File reportFolder = new File(testFolder.getAbsolutePath() + File.separator + reportFolderName);
        //Root Test Report Folder  [Example : Search HomeLoans Application Test.]
        LOGGER.info("creating directory:" + reportFolder );
        reportFolder.mkdir();

        File htmlTemplateFile = new File(reportFolder+File.separator+"MyReport.html");
        htmlTemplateFile.createNewFile();
        fullReportPath = reportFolder+File.separator+"MyReport.html";
        File screenShortFolder = new File( reportFolder+ File.separator + "Screenshots");
        if (!screenShortFolder.exists()) {
            LOGGER.info("creating directory: " + screenShortFolder);
            screenShortFolder.mkdir();
            screenshortReportPath =screenShortFolder.getAbsolutePath();
        }
    }


}

