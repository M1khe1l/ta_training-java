package com.epam.training.mikheil_akobidze.base;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    protected String URL = "https://www.saucedemo.com/";

    public void setUp(String browser) {
        log.info("Setting up browser: {}", browser);
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();         // create options for Chrome incognito window.
            options.addArguments("--incognito");
            options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");
            driverThread.set(new ChromeDriver(options));
        } else if (browser.equalsIgnoreCase("edge")) {
            driverThread.set(new EdgeDriver());
        }
        driverThread.get().manage().window().maximize();
        log.info("Navigating to URL: {}", URL);
        driverThread.get().get(URL);
    }

    public WebDriver getDriver() {
        return driverThread.get();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            log.info("Test FAILED: {}", result.getName());
            takeScreenshot();
        }
        if (getDriver() != null) {
            log.info("Closing Browser");
            getDriver().quit();
            driverThread.remove();
        }
    }


    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    // Triggers the test twice (once for Chrome, once for Edge) in parallel
    @DataProvider(name = "browserProvider", parallel = true)
    public Object[][] getBrowsers() {
        return new Object[][]{
                {"edge"},
                {"chrome"},
        };
    }
}
