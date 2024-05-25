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

public class RegisterPOM extends AbstractComponent {

	WebDriver driver;

	String[] Wtexts = { "First Name must be between 1 and 32 characters!",
			"Last Name must be between 1 and 32 characters!", "E-Mail Address does not appear to be valid!",
			"Telephone must be between 3 and 32 characters!", "Password must be between 4 and 20 characters!" };

	public RegisterPOM(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//div[@class='info']/span[contains(.,'My account')])[2]")
	WebElement myacc;

	@FindBy(xpath = "//span[contains(.,'Register')]")
	WebElement registerbutton;

	@FindBy(xpath = "//input[@name='firstname']")
	WebElement firstname;

	@FindBy(xpath = "//input[@name='lastname']")
	WebElement lastname;

	@FindBy(xpath = "//input[@name='email']")
	WebElement email;

	@FindBy(xpath = "//input[@name='telephone']")
	WebElement phone;

	@FindBy(xpath = "//input[@name='password']")
	WebElement passwrod;

	@FindBy(xpath = "//input[@name='confirm']")
	WebElement Cpassword;

	@FindBy(xpath = "//label[contains(.,'I have read and agree to the ')]")
	WebElement checkbox;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement submit;

	@FindBy(xpath = "//div/h1/following-sibling::p[2]")
	WebElement text;

	@FindBy(xpath = "//div[@class='text-danger']")
	List<WebElement> warings;

//	public void gotoRegister() {
//
//		Actions ac = new Actions(driver);
//		ac.moveToElement(myacc).build().perform();
//		registerbutton.click();
//	}

	public void register1() {

		gotoRegister();
		firstname.sendKeys("prasanna");
		lastname.sendKeys("kumar");
		email.sendKeys("prasanna230001009090@gmail.com");
		phone.sendKeys("9898989898");
		passwrod.sendKeys("0000");
		Cpassword.sendKeys("0000");
		checkbox.click();
		submit.click();

		waitforWebElement(text);
		String conformText = text.getText();

		Assert.assertEquals(conformText, "Congratulations! Your new account has been successfully created!",
				"not mathced");
	}

	public void validations() {

		gotoRegister();
		submit.click();
		List<String> warntexts = warings.stream().map(w -> w.getText()).collect(Collectors.toList());
		warntexts.forEach(a -> System.out.println(a));

		for (String value : warntexts) {
			if (containsValue(Wtexts, value)) {
				System.out.println(value + " matched with data");
			} else {
				System.out.println(value + " does not matched with data");
			}
		}
		
	}
	
	public static boolean containsValue(String[] Wtexts, String value) {
        for (String str : Wtexts) {
            if (str.equals(value)) {
                return true;
            }
        }
        return false;
    }
	
	public void wrongDetails() {
		
		gotoRegister();
		firstname.sendKeys("prasanna");
		lastname.sendKeys("kumar");
		email.sendKeys("prasannakumar230@gmail.com");
		phone.sendKeys("9898989898");
		passwrod.sendKeys("0000");
		Cpassword.sendKeys("1234");
		checkbox.click();
		submit.click();

		String emailwarning = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String passwordwaring = driver.findElement(By.cssSelector(".text-danger")).getText();
		
		Assert.assertEquals(emailwarning, "Warning: E-Mail Address is already registered!");
		Assert.assertEquals(passwordwaring, "Password confirmation does not match password!");
	}
	
	
	
	

	
}
