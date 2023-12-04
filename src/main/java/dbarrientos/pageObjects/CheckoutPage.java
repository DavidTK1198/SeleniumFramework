package dbarrientos.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractPage {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "first-name")
	WebElement firstName;
	@FindBy(id = "last-name")
	WebElement lastName;
	@FindBy(id = "postal-code")
	WebElement zipCode;
	@FindBy(id = "cancel")
	WebElement cancelButton;
	@FindBy(id = "continue")
	WebElement continueButton;


	public OverviewPage submitOrder(String firstName,String lastName,String zipCode) {
		this.firstName.sendKeys(firstName);
		this.lastName.sendKeys(lastName);
		this.zipCode.sendKeys(zipCode);
		continueButton.click();
		return new OverviewPage(driver);
	}
	
	public CartPage CancelOrder() {
		cancelButton.click();
		return new CartPage(driver);
	}
}
