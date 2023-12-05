package dbarrientos.pageObjects;

import java.util.function.Predicate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends AbstractPage {
	WebDriver driver;

	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".inventory_item")
	List<WebElement> products;
	@FindBy(id = "shopping_cart_container")
	WebElement cart;

	By productsBy = By.cssSelector(".inventory_item_description");
	By addToCart = By.cssSelector("button.btn_inventory");
	By remove = By.id("remove-sauce-labs-onesie");

	public List<WebElement> getProductList() {
		int waitTime = 2;
		waitForElementLocated(productsBy, waitTime);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(
				product -> product.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName))
				.findFirst().orElse(null);
		WebElement item = prod.findElement(By.xpath("./child::div[2]"));
		return item;
	}

	public void addProduct(String productName) throws InterruptedException {

		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();

	}

	public void visit() {

		driver.get("https://www.saucedemo.com/inventory.html");
	}

	public CartPage clickCart() {

		cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

}
