package dbarrientos.UITesting;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dbarrientos.pageObjects.ProductsPage;

public class LoginTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "sanity", "regression" })
	public void login(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductsPage productsPage = loginPage.login(input.get("email"), input.get("password"));
		Boolean match = productsPage.checkTitle();
		Assert.assertTrue(match);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String,String>> data = convertJsonToMap(System.getProperty("user.dir")+"//src//test//java//dbarrientos//data//PurchaseData.json");
		return new Object[][]  {{data.get(0)} };
		
	}
}
