package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.printf("====== STARTING TEST %s ======%n", iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.printf("====== FINISHED TEST %s Duration: %ss ======%n", iTestResult.getName(),
                getExecutionTime(iTestResult));
        WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        takeScreenshot(driver);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.printf("====== FAILED TEST %s Duration: %ss ======%n", iTestResult.getName(),
                getExecutionTime(iTestResult));
        WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        takeScreenshot(driver);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.printf("====== SKIPPING TEST %s ======%n", iTestResult.getName());
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}