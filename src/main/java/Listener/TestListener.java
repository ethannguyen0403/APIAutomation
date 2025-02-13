package Listener;

import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.AllureManager;

public class TestListener implements ITestListener {
    @Override
    public void onStart(ITestContext result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFinish(ITestContext result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        AllureManager.saveTextLog("Test case: " + result.getName() + "start running");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        AllureManager.saveTextLog("Test case: " + result.getName() + "success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
        AllureManager.saveTextLog("Test case: " + result.getName() + "failed");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub

    }
}
