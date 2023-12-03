package dbarrientos.pageObjects;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
	WebDriver driver;

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementLocated(By findby, int waitTime) {
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(waitTime));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}

	public void waitForWebElement(WebElement element, int waitTime) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForElementToDisappear(WebElement element, int waitTime) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		wait.until(ExpectedConditions.invisibilityOf(element));

	}
}
