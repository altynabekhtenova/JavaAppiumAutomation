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

public class SearchWordCheckTest {
    private AppiumDriver driver;
    private String searchWord;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
        capabilities.setCapability("appium:platformVersion", "16.0");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:app", "C:\\Users\\altyn\\OneDrive\\Рабочий стол\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\org.wikipedia_50550.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find skip button",
                2
        );
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void Test() {
        searchWord = "Java";

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search field",
                2
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                searchWord,
                "Cannot find input line",
                5
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'Java')]"),
                searchWord,
                "Cannot find " + searchWord
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                searchWord,
                "Cannot find " + searchWord
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'JavaScript')]"),
                searchWord,
                "Cannot find " + searchWord
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'Java version history')]"),
                searchWord,
                "Cannot find " + searchWord
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'Javanese language')]"),
                searchWord,
                "Cannot find " + searchWord
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'Javanese script')]"),
                searchWord,
                "Cannot find " + searchWord
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'Java (software platform)')]"),
                searchWord,
                "Cannot find " + searchWord
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'Javanese people')]"),
                searchWord,
                "Cannot find " + searchWord
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'JaVale McGee')]"),
                searchWord,
                "Cannot find " + searchWord
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'Java virtual machine')]"),
                searchWord,
                "Cannot find " + searchWord
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
        Assert.assertTrue(errorMessage, element.getText().toLowerCase().contains(expectedResult.toLowerCase()));
    }
}
