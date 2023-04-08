package org.aafalu.test.util.listners;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.aafalu.base.AafaluBaseTest;
import org.aafalu.utilities.extentreport.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends AafaluBaseTest implements ITestListener {
    ExtentTest extentTest;
    ExtentReports extent = ExtentManager.createExtentReports();
    ThreadLocal<ExtentTest> exTest = new ThreadLocal();

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extent.createTest(result.getName());
        extentTest.log(Status.INFO, "Test Started");
        exTest.set(extentTest);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        exTest.get().fail(result.getThrowable());
        Object testClass = result.getInstance();
        driver = ((AafaluBaseTest) testClass).getDriver();
        String base64Screenshot =takeScreenshotInCaseOFTestFailure(driver);
        //ExtentReports log and screenshot operations for failed tests.
        exTest.get().log(Status.FAIL, "Test Failed",
                exTest.get().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));


    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

    }

}