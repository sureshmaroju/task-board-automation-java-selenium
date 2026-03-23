package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.taskBoard.TaskBoardPage;
import pages.taskBoard.TaskToastNotifications;
import utilities.ScenarioContext;

/**
 * Step Definitions for Task Movement Feature
 * Mapped from move-task.feature
 */
public class TaskMovementSteps {
    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private TaskBoardPage taskBoardPage;
    private TaskToastNotifications toastNotifications;

    // Constructor with PicoContainer dependency injection
    public TaskMovementSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
        this.driver = scenarioContext.get("driver", WebDriver.class);
        this.taskBoardPage = new TaskBoardPage(driver);
        this.toastNotifications = new TaskToastNotifications(driver);
    }

    @Given("a task {string} exists in the To Do column")
    public void taskExistsInToDo(String taskTitle) {
        scenarioContext.set("taskTitle", taskTitle);
        // Verify task exists or create it
        if (!taskBoardPage.doesTaskExistInColumn("To Do", taskTitle)) {
            // In a real scenario, you'd create the task first
            throw new AssertionError("Task '" + taskTitle + "' does not exist in To Do column");
        }
    }

    @When("I click the menu button on task {string}")
    public void clickMenuOnTask(String taskTitle) {
        taskBoardPage.clickMenuOnTask(taskTitle);
    }

    @When("I click {string}")
    public void clickMenuItem(String menuItem) {
        if (menuItem.equals("Move Forward")) {
            taskBoardPage.clickMoveForwardButton();
        } else if (menuItem.equals("Delete")) {
            taskBoardPage.clickDeleteButton();
        }
    }

    @Then("a success toast should appear with message {string}")
    public void verifySuccessToast(String message) {
        toastNotifications.waitForToastWithMessage(message);
    }

    @Then("the task should appear in the In Progress column")
    public void verifyTaskInProgress() {
        String taskTitle = scenarioContext.get("taskTitle", String.class);
        taskBoardPage.waitForTaskToAppearInColumn("In Progress", taskTitle);
    }

    @Then("the task should NOT exist in the To Do column")
    public void verifyTaskNotInToDo() {
        String taskTitle = scenarioContext.get("taskTitle", String.class);
        taskBoardPage.waitForTaskToDisappearFromColumn("To Do", taskTitle);
    }

    @When("I move the task forward twice")
    public void moveTaskForwardTwice() {
        String taskTitle = scenarioContext.get("taskTitle", String.class);
        
        // Move 1: To Do -> In Progress
        System.out.println(taskTitle);
        taskBoardPage.clickMenuOnTask(taskTitle);
        taskBoardPage.clickMoveForwardButton();
        
        // Wait for transition
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Move 2: In Progress -> Done
        taskBoardPage.clickMenuOnTask(taskTitle);
        taskBoardPage.clickMoveForwardButton();
    }

    @Then("the task should appear in the Done column")
    public void verifyTaskInDone() {
        String taskTitle = scenarioContext.get("taskTitle", String.class);
        taskBoardPage.waitForTaskToAppearInColumn("Done", taskTitle);
    }

    @Given("a task exists in the To Do column")
    public void taskExistsInToDoGeneric() {
        String taskTitle = taskBoardPage.getFirstTaskTitleInColumn("To Do");
        scenarioContext.set("taskTitle", taskTitle);
    }

    @Given("a task exists in the Done column")
    public void taskExistsInDone() {
        String taskTitle = taskBoardPage.getFirstTaskTitleInColumn("Done");
        scenarioContext.set("taskTitle", taskTitle);
    }

    @When("I click the menu button on that task")
    public void clickMenuOnThatTask() {
        String taskTitle = scenarioContext.get("taskTitle", String.class);
        taskBoardPage.clickMenuOnTask(taskTitle);
    }

    @Then("the {string} button should NOT be visible")
    public void verifyButtonNotVisible(String buttonName) {
        boolean isVisible = taskBoardPage.isMoveForwardButtonVisible();
        assert !isVisible : "Expected button '" + buttonName + "' to not be visible";
    }

    @Then("only the Delete button should be available")
    public void verifyOnlyDeleteAvailable() {
        boolean isVisible = taskBoardPage.isDeleteButtonVisible();
        System.out.println(isVisible);
        assert isVisible : "Expected button - Delete to be visible";
    }
}
