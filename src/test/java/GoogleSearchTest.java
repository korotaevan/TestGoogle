import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchTest<WebDriver> {
    private ChromeDriver driver;
    @Before
    public void setUp() {
// Launch a new Firefox instance
        System.getProperty("webdriver.chrome.driver","C:\\Windows\\chromedriver.exe");
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("headless");
        //driver = new ChromeDriver(options);
        driver = new ChromeDriver();
// Maximize the browser window
        driver.manage().window().maximize();
// Navigate to Google
        driver.get("http://www.google.com");
    }
    @Test
    public void testGoogleSearch() {
        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));
        // Clear the existing text value
        element.clear();
        // Enter something to search for
        element.sendKeys("Cheese!");
        // Now submit the form
        element.submit();
        System.out.println("Page title is: " + driver.getTitle());
        // Google's search is rendered dynamically with JavaScript.
        // wait for the page to load, timeout after 10 seconds
       (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
           public Boolean apply(org.openqa.selenium.WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });
        System.out.println("Page title is: " + driver.getTitle());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @After
    public void tearDown() throws Exception {
// Close the browser
        driver.quit();
    }
}
