package pages.taskBoard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

/**
 * Task Toast Notifications Page Object
 * Handles toast message notifications
 */
public class TaskToastNotifications extends BasePage {

    // Locators
    private By toastContainerLocator = By.className("toast-container");

    private By toastMessageLocator(String message) {
        return By.xpath("//*[contains(@class, 'toast') and contains(text(), '" + message + "')]");
    }

    // Constructor
    public TaskToastNotifications(WebDriver driver) {
        super(driver);
    }

    // Methods
    public void waitForToastWithMessage(String message) {
        waitForElement(toastMessageLocator(message));
    }

    public void waitForToastToDisappear(String message) {
        waitForElementToDisappear(toastMessageLocator(message));
    }

    public boolean isToastDisplayed(String message) {
        return isDisplayed(toastMessageLocator(message));
    }

    public String getToastMessage() {
        return getText(toastMessageLocator(""));
    }

    public void waitForToastToAutomaticallyDisappear(String message) {
        // Toast typically disappears after 3-5 seconds
        waitForToastWithMessage(message);
        waitForToastToDisappear(message);
    }
}
