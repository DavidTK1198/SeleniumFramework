package dbarrientos.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstractPage {
	WebDriver driver;

	@FindBy(id = "checkout")
	WebElement checkoutButton;

	@FindBy(css = ".cart_item_label :nth-child(2)")
	private List<WebElement> cartProducts;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public Boolean VerifyProducts(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}

	public CheckoutPage goToCheckout() {
		checkoutButton.click();
		return new CheckoutPage(driver);

	}

	public void visit() {

		driver.get("https://www.saucedemo.com/cart.html");
	}

}
