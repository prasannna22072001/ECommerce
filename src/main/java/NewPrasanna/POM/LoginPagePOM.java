package NewPrasanna.POM;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPagePOM extends AbstractComponent {

	WebDriver driver;
//
//	String[] data = {"prasannakumar2300@gmail.com","0000"};
//	String[] Wrongdata = {"prasanna@gmail.com","00001"};
//	String realMessage = "Warning: No match for E-Mail Address and/or Password.";

	public LoginPagePOM(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}
	
	@FindBy(id="input-email")
	WebElement email;

	@FindBy(id="input-password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement submit;
	
	@FindBy(css=".alert.alert-danger.alert-dismissible")
	WebElement warningMessage;
	
	@FindBy(xpath="//span[contains(.,'Home')]")
	WebElement home;
	
	public void login(String mail ,String pass ) {
		
		gotoLogin();
		waitforWebElement(email);
		email.sendKeys(mail);
		password.sendKeys(pass);
		submit.click();
	}
	
	public void wronglogins(String[] Wrongdata , String realMessage) {
		
		gotoLogin();
		waitforWebElement(email);
		email.sendKeys(Wrongdata[0]);
		password.sendKeys(Wrongdata[1]);
		submit.click();
		
		Assert.assertEquals(warningMessage.getText(), realMessage);;
	}

	public MainPagePOM goToHome(String email, String password) {
		
		login(email,password);
		waitforWebElement(home);

		home.click();
		MainPagePOM Mpage = new MainPagePOM(driver);
		return Mpage;
		
	}
	
}
