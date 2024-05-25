package NewPrasanna.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import NewPrasanna.ECommerce.BaseTest;
import NewPrasanna.POM.LoginPagePOM;
import NewPrasanna.POM.RegisterPOM;

public class RegisterPageTest extends BaseTest {
	 
	
	@Test
	public void registerTestCase1() throws IOException {
		RegisterPOM register = new RegisterPOM(driver);
					
		register.gotoRegister();
		register.register1();
		
	}
	
	@Test
	public void waringsTest() {
		RegisterPOM register = new RegisterPOM(driver);
		
		register.validations();
	}
	
	
	@Test
	public void wrongdetails() {
		RegisterPOM register = new RegisterPOM(driver);
		
		register.wrongDetails();
	}
	
	
}
