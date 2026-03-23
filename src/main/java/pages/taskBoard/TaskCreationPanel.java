package pages.taskBoard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

/**
 * Task Creation Panel Page Object
 * Handles interactions with the task creation form
 */
public class TaskCreationPanel extends BasePage {

    // Locators
    private By titleInputLocator = By.id("task-title");
    private By descriptionInputLocator = By.id("task-description");
    private By prioritySelectLocator = By.id("priority-select");
    private By addTaskButtonLocator = By.id("submit-task");
    private By titleErrorMessageLocator = By.xpath("//span[contains(@class, 'error-message') and contains(text(), 'Title')]");

    // Constructor
    public TaskCreationPanel(WebDriver driver) {
        super(driver);
    }

    // Methods
    public void enterTitle(String title) {
        enterText(titleInputLocator, title);
    }

    public void enterDescription(String description) {
        enterText(descriptionInputLocator, description);
    }

    public void selectPriority(String priority) {
    	
    	selectByVisibleText(prioritySelectLocator, priority);
    }

    public void clickAddTaskButton() {
        click(addTaskButtonLocator);
    }

    public void fillTaskForm(String title, String description, String priority) {
        enterTitle(title);
        enterDescription(description);
        selectPriority(priority);
    }

    public void fillTaskFormWithTitle(String title) {
        enterTitle(title);
    }

    public boolean isTitleErrorVisible() {
        return isDisplayed(titleErrorMessageLocator);
    }

    public void clearTitle() {
        driver.findElement(titleInputLocator).clear();
    }

    public void waitForAddTaskButtonToBeClickable() {
        waitForClickable(addTaskButtonLocator);
    }
}
