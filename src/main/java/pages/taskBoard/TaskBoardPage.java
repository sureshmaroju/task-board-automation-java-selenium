package pages.taskBoard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

/**
 * Task Board Page Object
 * Handles interactions with the main dashboard
 */
public class TaskBoardPage extends BasePage {
    
    // Locators
    private By columnLocator(String columnName) {
        return By.xpath("//div[contains(@class, 'column-header') and contains(text(), '" + columnName + "')]//ancestor::div[contains(@class, 'column')]");
    }

    private By taskInColumnLocator(String columnName, String taskTitle) {
        return By.xpath("//app-task-column[@title='" + columnName + "']//*[contains(@class,'task-title') and contains(text(), '" + taskTitle + "')]");
      
    }

    private By menuButtonOnTaskLocator(String taskTitle) {
        return By.xpath("//*[contains(text(), '" + taskTitle + "')]/ancestor::*[contains(@class, 'task-card')]//button[contains(@class,'btn-menu')]");
    }

    private By firstTaskInColumnLocator(String columnName) {
        return By.xpath("(//h3[contains(@class, 'column-header') and contains(text(), '" + columnName + "')]//ancestor::div[contains(@class, 'column')]//div[contains(@class, 'task-card')])[1]");
    }
    
    private By firstTaskTitleInColumnLocator(String columnName) {
        return By.xpath("(//h3[contains(@class, 'column-header') and contains(text(), '" + columnName + "')]//ancestor::div[contains(@class, 'column')]//div[contains(@class, 'task-card')]//*[@class='task-title'])[1]");
    }

    private By moveForwardButtonLocator = By.xpath("//button[contains(text(), 'Move Forward')]");
    private By deleteButtonLocator = By.xpath("//button[contains(text(), 'Delete')]");

    // Constructor
    public TaskBoardPage(WebDriver driver) {
        super(driver);
    }

    // Methods
    public void loadPage() {
        super.loadPage();
//        waitForElement(By.className("task-board"));
    }

    public boolean doesTaskExistInColumn(String columnName, String taskTitle) {
        try {
            return isDisplayed(taskInColumnLocator(columnName, taskTitle));
        } catch (Exception e) {
            return false;
        }
    }

    public void clickMenuOnTask(String taskTitle) {
    	By ele = menuButtonOnTaskLocator(taskTitle);
        click(ele);
    }

    public void clickMoveForwardButton() {
        click(moveForwardButtonLocator);
    }

    public void clickDeleteButton() {
        click(deleteButtonLocator);
    }

    public boolean isMoveForwardButtonVisible() {
        return isDisplayed(moveForwardButtonLocator);
    }
    
    public boolean isDeleteButtonVisible() {
        return isDisplayed(deleteButtonLocator);
    }


    public String getFirstTaskTitleInColumn(String columnName) {
		return getText(firstTaskTitleInColumnLocator(columnName)).trim();
    }

    public int getColumnCount() {
        return getElements(By.className("column")).size();
    }

    public void waitForTaskToAppearInColumn(String columnName, String taskTitle) {
        waitForElement(taskInColumnLocator(columnName, taskTitle));
    }

    public void waitForTaskToDisappearFromColumn(String columnName, String taskTitle) {
        waitForElementToDisappear(taskInColumnLocator(columnName, taskTitle));
    }

    public WebElement getTaskCard(String taskTitle) {
        return driver.findElement(By.xpath("//*[contains(@class, 'task-card') and contains(text(), '" + taskTitle + "')]"));
    }

    public String getTaskPriorityBadge(String taskTitle) {
        WebElement priorityBadge = driver.findElement(By.xpath("//span[contains(text(), '"+taskTitle+"')]/ancestor::div[contains(@class, 'task-card')]//span[contains(@class,'priority-badge')]"));
        return priorityBadge.getText();
    }
}
