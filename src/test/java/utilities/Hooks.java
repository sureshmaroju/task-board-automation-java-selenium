package utilities;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Hooks for browser setup and teardown
 * Executed before and after each scenario
 */
public class Hooks {
    private WebDriver driver;
    private ScenarioContext scenarioContext;

    // Constructor with PicoContainer dependency injection
    public Hooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Before
    public void setUp() {
        // Use Chrome by default (can be parameterized)
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Configure driver
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        // Store in context for access in step definitions
        scenarioContext.set("driver", driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        scenarioContext.clear();
    }
}
