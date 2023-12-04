package dbarrientos.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OverviewPage extends AbstractPage {

	WebDriver driver;

	public OverviewPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "cancel")
	WebElement cancelButton;
	@FindBy(id = "finish")
	WebElement finishButton;
	

	public OrderCompletePage finishOrder() {
		finishButton.click();
		return new OrderCompletePage(driver);
	}


}
