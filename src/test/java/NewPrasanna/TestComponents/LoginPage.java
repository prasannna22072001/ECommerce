package NewPrasanna.TestComponents;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import NewPrasanna.ECommerce.BaseTest;
import NewPrasanna.POM.CartPOM;
import NewPrasanna.POM.LoginPagePOM;
import NewPrasanna.POM.MainPagePOM;

public class LoginPage extends BaseTest {

	String[] Wrongdata = { "prasannaahdlfj@gmail.com", "00001" };
	String realMessage = "Warning: No match for E-Mail Address and/or Password.";
	String[] addtocartProd = { "iPod Shuffle", "iMac", "MacBook Air" };

	@Test(dataProvider = "getdata")
	public void EnterloginDetails(HashMap<String,String> input) {

		LoginPagePOM Lpage = new LoginPagePOM(driver);
		Lpage.login(input.get("email"), input.get("password"));
	}

	@Test
	public void EnterWrongLogins() {
		LoginPagePOM Lpage = new LoginPagePOM(driver);
		Lpage.wronglogins(Wrongdata, realMessage);
	}

	@Test /* (dependsOnMethods = "EnterloginDetails") */(groups = { "Big Test" }, dataProvider = "getdata")
	public void mainTest(HashMap<String,String> input) throws InterruptedException {

		LoginPagePOM Lpage = new LoginPagePOM(driver);
		MainPagePOM Mpage = Lpage.goToHome(input.get("email"), input.get("password")); // here i created object for mainpagepom class in loginpagepom class . So that's y it is returing object

		

		Mpage.productPage();
		Mpage.productDetails();
		Mpage.addtocart(addtocartProd);
		Mpage.remove(addtocartProd);

		CartPOM cartP = new CartPOM(driver);
		cartP.cart(addtocartProd);
		cartP.productsDetails();
		cartP.deleteproduct(input.get("productName"));

	}

	@DataProvider
	public Object[][] getdata() throws IOException {

//		return new Object[][] { { "prasannakumar2300@gmail.com", "0000", "iMac" },
//				{ "fhnasjknl@gmail.com", "1234", "MacBook Air" } };
		
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//NewPrasanna//data//Data.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };	
	}

}
