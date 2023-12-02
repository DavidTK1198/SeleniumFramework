package dbarrientos.pageObjects;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonsMethods {
	WebDriver driver;

	public CommonsMethods(WebDriver driver) {
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

	public void takeScreenshoot(String testCaseName) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\screenshots\\" + testCaseName + ".png";
		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
