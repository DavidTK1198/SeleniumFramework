package dbarrientos.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage{
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
		
	@FindBy(id="user-name")
	WebElement userEmail;
	
	@FindBy(id="password")
	WebElement passwordEle;
	
	@FindBy(id="login-button")
	WebElement submit;
	@FindBy(css="[class*='.error-message-container']")
	WebElement errorMessage;

	
	public ProductsPage login(String email,String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductsPage productCatalogue = new ProductsPage(driver);
		return productCatalogue;
		
		
	}
	
	public String getErrorMessage()
	{
		int waitTime=2;
		waitForWebElement(errorMessage,waitTime);
		return errorMessage.getText();
	}
	
	public void visit()
	{
		driver.get("https://www.saucedemo.com/");
	}
	
	
}
