package Tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.Objects;

public class AssertTitleTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
        capabilities.setCapability("appium:platformVersion", "16.0");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:app", "C:\\Users\\altyn\\OneDrive\\Рабочий стол\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\Wikipedia_2.7.50449.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void Test() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find skip button",
                2
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search field",
                2
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find input line",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@class='android.view.ViewGroup'][2]"),
                "Cannot find article of #2",
                2
        );

        assertElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "pcs-edit-section-title-description",
                "Cannot find resource-id of article #2",
                "resource-id"
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find 'Navigate up' button",
                2
        );

        waitForElementAndClick(
                By.xpath("//*[@class='android.view.ViewGroup'][3]"),
                "Cannot find article of #3",
                2
        );

        assertElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "pcs-edit-section-title-description",
                "Cannot find resource-id of article #3",
                "resource-id"
        );
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 20);
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value,
                                                 String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private void assertElementHasText(By by, String expectedResult, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage);
        Assert.assertEquals(errorMessage, expectedResult, element.getText());
    }

    private void assertElementPresent(By by, String expectedResult, String errorMessage, String attribute) {
        WebElement element = waitForElementPresent(by, errorMessage);
        Assert.assertEquals(errorMessage, expectedResult, element.getAttribute(attribute));
    }
}
