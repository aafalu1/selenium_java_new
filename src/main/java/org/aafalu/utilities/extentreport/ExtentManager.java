package org.aafalu.utilities.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.aafalu.utilities.App;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        String directory= App.Exient_Report;
        ExtentSparkReporter reporter = new ExtentSparkReporter(directory);
        reporter.config().setReportName("Aafalu Automation Report");
        reporter.config().setTheme(Theme.DARK);
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Blog Name", "Aafalu Automation");
        extentReports.setSystemInfo("Author", "Aafalu");
        return extentReports;
    }
}
