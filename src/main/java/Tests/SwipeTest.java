package Tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;

public class SwipeTest {
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
        waitForElementPresent(
                By.xpath("//*[contains(@text,'The Free Encyclopedia\n…in over 300 languages')]"),
                "Cannot find title 'The Free Encyclopedia\n…in over 300 languages' of page",
                2
        );

        swipeLeftToFindElement(
                By.xpath("//*[contains(@text,'New ways to explore')]"),
                "Cannot find title 'New ways to explore' of page",
                20
        );

        swipeLeftToFindElement(
                By.xpath("//*[contains(@text,'Reading lists with sync')]"),
                "Cannot find title 'Reading lists with sync' of page",
                20
        );

        swipeLeftToFindElement(
                By.xpath("//*[contains(@text,'Send anonymous data')]"),
                "Cannot find title 'Send anonymous data' of page",
                20
        );

        assertElementHasTextAndClick(
                By.xpath("//*[contains(@text,'Accept')]"),
                "Accept",
                "Cannot find 'Accept' button"
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "Cannot find 'Search Wikipedia'"
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

    private void assertElementHasTextAndClick(By by, String expectedResult, String errorMessage) {
        assertElementHasText(by, expectedResult, errorMessage);
        waitForElementAndClick(by, errorMessage, 2);
    }

    protected void swipeLeft(int timeOfSwipe) {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.80);
        int endX = (int) (size.width * 0.20);
        int startY = size.height / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(),
                startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(timeOfSwipe),
                PointerInput.Origin.viewport(),
                endX, startY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    protected void swipeLeftQuick() {
        swipeLeft(200);
    }

    protected void swipeLeftToFindElement(By by, String errorMessage, int maxSwipe) {
        int alreadySwipe = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwipe > maxSwipe) {
                waitForElementPresent(by, "Cannot find element by swipping left. \n" + errorMessage, 0);
                return;
            }
            swipeLeftQuick();
            ++alreadySwipe;
        }
    }
}
