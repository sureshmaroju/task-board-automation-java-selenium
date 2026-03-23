@move-task @workflow
Feature: Task Movement

  Scenario: Move task from To Do to In Progress
    Given the Task Board page is loaded
    And a task "Create API endpoints" exists in the To Do column
    When I click the menu button on task "Create API endpoints"
    And I click "Move Forward"
    Then a success toast should appear with message "Task moved successfully"
    And the task should appear in the In Progress column
    And the task should NOT exist in the To Do column

  Scenario: Move task through all columns to Done
    Given the Task Board page is loaded
    And a task exists in the To Do column
    When I move the task forward twice
    Then the task should appear in the Done column

  Scenario: Move Forward not available for Done tasks
    Given the Task Board page is loaded
    And a task exists in the Done column
    When I click the menu button on that task
    Then the "Move Forward" button should NOT be visible
    And only the Delete button should be available
