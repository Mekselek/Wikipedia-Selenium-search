import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;



public class WikipediaTest {
    @Test
    public void wikipediaSearchTest() {

        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.google.com");
        driver.manage().window().maximize();
        WebElement acceptCookies = driver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
        acceptCookies.click();
        WebElement searchField = driver.findElement(By.name("q"));

        new Actions(driver)
                .sendKeys(searchField, "Wikipedia")
                .sendKeys(searchField, Keys.ENTER)
                .perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/span[text()='Wikipedia']")));

        driver.findElement(By.xpath("//div/span[text()='Wikipedia']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));

        WebElement wikiSearchField = driver.findElement(By.name("search"));

        String valueAssertion = wikiSearchField.getAttribute("placeholder");
        String expectedPlaceholderValue = "Przeszukaj Wikipedię";

        Assert.assertEquals(expectedPlaceholderValue, valueAssertion);

        new Actions(driver)
                .sendKeys(wikiSearchField, "Kasztanowiec")
                .sendKeys(wikiSearchField, Keys.ENTER)
                .perform();

        WebElement titleName = driver.findElement(By.className("mw-page-title-main"));
        String titleText = titleName.getText();
        String exp = "Kasztanowiec";
        Assert.assertEquals(exp, titleText);
    }

}
