package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Base page object with common Selenium operations
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int TIMEOUT_SECONDS = 10;
    private static final String BASE_URL = "https://dbaloyverse.github.io/qa-authomation-assignment/";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
    }

    // Wait for element to be visible
    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be clickable
    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Click element with wait
    protected void click(By locator) {
        waitForClickable(locator).click();
    }
    
    protected void selectByVisibleText(By locator, String text) {
    	WebElement element = waitForElement(locator);
    	Select select = new Select(element);
    	select.selectByVisibleText(text);
    }

    // Enter text
    protected void enterText(By locator, String text) {
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Get text
    protected String getText(By locator) {
        return waitForElement(locator).getText();
    }

    // Check if element is displayed
    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Navigate to URL
    protected void navigate(String url) {
        driver.navigate().to(url);
    }

    // Load base page
    protected void loadPage() {
        navigate(BASE_URL);
    }

    // Wait for element to disappear
    protected void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Get all matching elements
    protected java.util.List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    // Wait for text in element
    protected void waitForText(By locator, String text) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
}
