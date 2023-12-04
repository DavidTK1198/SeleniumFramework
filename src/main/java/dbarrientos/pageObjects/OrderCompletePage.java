package dbarrientos.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderCompletePage extends AbstractPage {
	WebDriver driver;
	static final String headerText = "Thank you for your order!";
	static final String bodyText = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

	public OrderCompletePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "back-to-products")
	WebElement backButton;
	@FindBy(css = ".complete-header")
	WebElement header;
	@FindBy(css = ".complete-text")
	WebElement body;

	public ProductsPage backToHome(String email, String password) {

		backButton.click();
		ProductsPage productCatalogue = new ProductsPage(driver);
		return productCatalogue;

	}

	public boolean validateMessages() {
		Boolean match = header.getText().equals(headerText) && body.equals(body);
		return match;
	}
}
