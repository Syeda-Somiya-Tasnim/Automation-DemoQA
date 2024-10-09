import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)



public class JUnitPart1 {
    WebDriver driver;
    @BeforeAll
    public void setup() {

        // Initialize the Chrome WebDriver
        driver = new ChromeDriver();
        // Maximize browser window
        driver.manage().window().maximize();
        // Set an implicit wait of 30 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    @DisplayName("Visit URL and check if title is showing properly")
    @Test
    public void visitURL(){
        driver.get("https://demoqa.com/elements");
        // Get the title of the page
        String actualTitle = driver.getTitle();
        String currentURL = driver.getCurrentUrl();
        // Expected title
        String expectedTitle = "DEMOQA";

        // Assert that the actual title matches the expected title
        assertEquals(expectedTitle, actualTitle);

        // Check if currentURL is not null before using contains
        Assertions.assertNotNull(currentURL, "URL should not be null");
        Assertions.assertTrue(currentURL.contains("elements"), "URL should contain 'elements'");
        System.out.println(driver.getPageSource());

    }


    @Test
    public void formAutomation() {
        driver.get("https://demoqa.com/text-box");
        // Fill the username field
        driver.findElement(By.id("userName")).sendKeys("Test User");

        // Fill the email field
        driver.findElement(By.cssSelector("[type=email]")).sendKeys("test@test.com");

        // Fill address fields (assuming multiple address inputs)
        List<WebElement> addressElem = driver.findElements(By.className("form-control"));
        addressElem.get(2).sendKeys("Gulshan");
        addressElem.get(3).sendKeys("Dhaka");

        // Scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");

        // Click the second button (assuming multiple buttons exist)
        driver.findElements(By.tagName("button")).get(1).click();

        List<WebElement> resultElem = driver.findElements(By.className("mb-1"));
        String val1 = resultElem.get(0).getText();
        String val2 = resultElem.get(1).getText();
        String val3 = resultElem.get(2).getText();
        String val4 = resultElem.get(3).getText();

        Assertions.assertTrue(val1.contains("Test User"));
        Assertions.assertTrue(val2.contains("test@test.com"));
        Assertions.assertTrue(val3.contains("Gulshan"));
        Assertions.assertTrue(val4.contains("Dhaka"));

    }

    //@AfterAll
    public void tearDown(){
        driver.quit();

    }
}
