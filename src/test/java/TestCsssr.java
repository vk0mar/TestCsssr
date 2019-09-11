import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestCsssr {
    private WebDriver driver;
    private WebDriverWait wait;
    private String linkLocator  = "div.graphs-errors a";
    private String blockLocator = "div.info-errors";
    private String startPage = "http://blog.csssr.ru/qa-engineer/";

    private void waitOpacity(String locator){
        wait.until((WebDriver d)->(d.findElement(By.cssSelector(locator)).getAttribute("style").indexOf("opacity")<=0));
    }

    @Before
    public void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "bin//chromedriver");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //неявное
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void testCsssr() {
        driver.get(startPage);

        driver.findElement(By.cssSelector(linkLocator)).click();
        waitOpacity(blockLocator);

        driver.findElement(By.cssSelector(linkLocator)).click();
        waitOpacity(blockLocator);

        Assert.assertTrue(driver.findElement(By.cssSelector(blockLocator)).isDisplayed());
    }

    @After
    public void tearDown() {
        if (driver!=null){driver.quit();}
    }
}
