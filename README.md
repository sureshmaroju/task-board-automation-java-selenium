# Java Selenium + Cucumber - Task Board Automation

A **proof-of-concept** Java Selenium 4 + Cucumber 7 implementation of the task board automation framework, demonstrating a complete migration from the Playwright BDD version.

## Quick Start

### Prerequisites
- Java 11+
- Maven 3.6+
- Chrome/Firefox browser

### Get Running in 5 Minutes

```bash
# 1. Navigate to project
cd task-board-automation-java-selenium

# 2. Install dependencies
mvn clean install

# 3. Run PoC tests (Task Creation & Movement)
mvn test -Dtags="@smoke"

# 4. View results
open target/cucumber-report.html
```


## What's Included

### ✅ Features
- **create-a-new-task.feature** - Task creation with form validation
- **move-task.feature** - Task movement through workflow columns

### ✅ Framework Infrastructure (Ready for Scale)
- **Page Objects** - 3 components for task board interactions
- **Step Definitions** - 2 step definition classes
- **Utilities** - ScenarioContext + Hooks for lifecycle management
- **Maven Configuration** - Ready for CI/CD integration
- **Reporter** - HTML + JSON reports in `/target`

## Key Features

### 🔧 Technology Stack
- **Selenium 4.15.0** - Browser automation
- **Cucumber Java 7.14.0** - BDD framework
- **WebDriverManager** - Auto driver management
- **PicoContainer** - Dependency injection
- **JUnit 4** - Test execution

### 📊 Dependency Injection
Uses PicoContainer for automatic constructor injection:

```java
public class TaskCreationSteps {
    private WebDriver driver;
    private ScenarioContext scenarioContext;
    
    // Constructor-based DI via PicoContainer
    public TaskCreationSteps(ScenarioContext context) {
        this.scenarioContext = context;
        this.driver = context.get("driver", WebDriver.class);
    }
}
```

### 📝 Page Object Model
Each component has single responsibility:

```java
// Separate concerns
TaskBoardPage           // Dashboard navigation
TaskCreationPanel       // Form handling
TaskToastNotifications  // Toast verification
```

### 🎯 Shared Context Pattern
Uses `ScenarioContext` for data sharing between steps:

```java
@When("I enter {string} in the title field")
public void enterTitle(String title) {
    taskCreationPanel.enterTitle(title);
    scenarioContext.set("currentTaskTitle", title);  // Store for later
}

@Then("the task {string} should appear in the To Do column")
public void verifyTaskAppears(String taskTitle) {
    String storedTitle = scenarioContext.get("currentTaskTitle", String.class);
    taskBoardPage.waitForTaskToAppearInColumn("To Do", taskTitle);
}
```

## Running Tests

### Run All PoC Tests
```bash
mvn test -Dtags="@smoke"
```

### Run Specific Feature
```bash
mvn test -Dtags="@create-task"
mvn test -Dtags="@move-task"
```

### Run with More Details
```bash
mvn test -X  # Verbose output
```

### Generate Reports Only
```bash
mvn test -DskipTests  # Compile but don't run
```

## Configuration

### Changing Browser
Edit `Hooks.java`:

```java
@Before
public void setUp() {
    // ✅ Chrome (default)
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    
    // 🦊 Or Firefox
    // WebDriverManager.firefoxdriver().setup();
    // driver = new FirefoxDriver();
}
```

## Common Commands

```bash
# Setup
mvn clean install

# Test Execution
mvn test                              # All scenarios
mvn test -Dtags="@smoke"             # Smoke tests
mvn test -Dtags="@create-task"       # Specific feature

# Reports
mvn test  # HTML report: target/cucumber-report.html
          # JSON report: target/cucumber-report.json

# Debugging
mvn test -X                           # Verbose output
mvn test -DfailIfNoTests=false       # Continue on missing tests

# Clean
mvn clean                             # Remove target/
mvn clean install -DskipTests         # Clean build without tests
```

## Development Workflow

1. **Write Feature** in `src/test/resources/features/`
2. **Run Tests** → Red (undefined steps)
3. **Create Step Definition** → Yellow (implemented but failing)
4. **Create Page Object** → Green (all passing)
5. **Refactor** → Maintain patterns
