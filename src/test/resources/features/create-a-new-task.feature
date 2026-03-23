@smoke @create-task
Feature: Task Creation

  Scenario: Successfully create a new task with all fields
    Given the Task Board page is loaded
    When I enter "Implement login feature" in the title field
    And I enter "Add OAuth2 authentication flow" in the description field
    And I select "High" priority
    And I click the Add Task button
    Then a success toast should appear with message "Task created successfully"
    And the task "Implement login feature" should appear in the To Do column
    And the task should display "High" priority badge

  Scenario: Create task with only required fields
    Given the Task Board page is loaded
    When I enter "Quick task" in the title field
    And I click the Add Task button
    Then the task "Quick task" should appear in the To Do column
