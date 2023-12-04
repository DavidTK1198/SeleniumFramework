package dbarrientos.UITesting;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dbarrientos.pageObjects.CartPage;
import dbarrientos.pageObjects.CheckoutPage;
import dbarrientos.pageObjects.OrderCompletePage;
import dbarrientos.pageObjects.OverviewPage;
import dbarrientos.pageObjects.ProductsPage;

public class SubmitOrderTest extends BaseTest{


	@Test(dataProvider="getData",groups= {"sanity", "regression"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{

		
		ProductsPage productsPage = loginPage.login(input.get("email"), input.get("password"));
		productsPage.addProduct(input.get("product"));
		CartPage cartPage = productsPage.clickCart();
		Boolean match = cartPage.VerifyProducts(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		OverviewPage overviewPage = checkoutPage.submitOrder(input.get("firstN"),input.get("lastN"),input.get("zip"));
		OrderCompletePage orderComplete = overviewPage.finishOrder();
		Assert.assertTrue(orderComplete.validateMessages());
		
		

	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String,String>> data = convertJsonToMap(System.getProperty("user.dir")+"//src//test//java//dbarrientos//data//PurchaseData.json");
		return new Object[][]  {{data.get(0)} };
		
	}
}
