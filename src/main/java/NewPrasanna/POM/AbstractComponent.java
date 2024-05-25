package NewPrasanna.POM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;
	Actions ac ;
	
	
	public AbstractComponent(WebDriver driver) {

	this.driver=driver;
	PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//div[@class='info']/span[contains(.,'My account')])[2]")
	WebElement myacc;

	@FindBy(xpath = "//span[contains(.,'Register')]")
	WebElement registerbutton;
	
	@FindBy(xpath = "//span[contains(.,'Login')]")
	WebElement loginbutton;
	
	public void waitforWebElement(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	public void gotoRegister() {

		ac = new Actions(driver);
		ac.moveToElement(myacc).build().perform();
		registerbutton.click();
	}

	public void gotoLogin() {
		ac = new Actions(driver);
		ac.moveToElement(myacc).build().perform();
		loginbutton.click();
	}
}

