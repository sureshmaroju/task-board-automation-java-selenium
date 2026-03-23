package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.taskBoard.TaskBoardPage;
import pages.taskBoard.TaskCreationPanel;
import pages.taskBoard.TaskToastNotifications;
import utilities.ScenarioContext;

/**
 * Step Definitions for Task Creation Feature
 * Mapped from create-a-new-task.feature
 */
public class TaskCreationSteps {
    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private TaskBoardPage taskBoardPage;
    private TaskCreationPanel taskCreationPanel;
    private TaskToastNotifications toastNotifications;

    // Constructor with PicoContainer dependency injection
    public TaskCreationSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
        this.driver = scenarioContext.get("driver", WebDriver.class);
        this.taskBoardPage = new TaskBoardPage(driver);
        this.taskCreationPanel = new TaskCreationPanel(driver);
        this.toastNotifications = new TaskToastNotifications(driver);
    }

    @Given("the Task Board page is loaded")
    public void loadTaskBoardPage() {
        taskBoardPage.loadPage();
    }

    @When("I enter {string} in the title field")
    public void enterTitleField(String title) {
        taskCreationPanel.enterTitle(title);
        scenarioContext.set("currentTaskTitle", title);
    }

    @When("I enter {string} in the description field")
    public void enterDescriptionField(String description) {
        taskCreationPanel.enterDescription(description);
        scenarioContext.set("currentTaskDescription", description);
    }

    @When("I select {string} priority")
    public void selectPriority(String priority) {
        taskCreationPanel.selectPriority(priority);
        scenarioContext.set("currentTaskPriority", priority);
    }

    @When("I click the Add Task button")
    public void clickAddTaskButton() {
        taskCreationPanel.clickAddTaskButton();
    }

//    @Then("a success toast should appear with message {string}")
//    public void verifySuccessToast(String message) {
//        toastNotifications.waitForToastWithMessage(message);
//    }

    @Then("the task {string} should appear in the To Do column")
    public void verifyTaskInToDo(String taskTitle) {
        taskBoardPage.waitForTaskToAppearInColumn("To Do", taskTitle);
    }

    @Then("the task should display {string} priority badge")
    public void verifyPriorityBadge(String priority) {
        String taskTitle = scenarioContext.get("currentTaskTitle", String.class);
        String priorityBadge = taskBoardPage.getTaskPriorityBadge(taskTitle).toLowerCase();
        assert priorityBadge.contains(priority.toLowerCase()) : "Expected priority badge to contain " + priority + " but got " + priorityBadge;
    }
}
