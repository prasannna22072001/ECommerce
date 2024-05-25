package NewPrasanna.POM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPagePOM extends AbstractComponent {

	WebDriver driver;
	Actions ac;

	

	public MainPagePOM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//span[contains(.,'Mega Menu')]")
	WebElement megamenu;

	@FindBy(xpath = "//a[@title='Apple']")
	WebElement apple;

	@FindBy(xpath = "//a[@class='text-ellipsis-2']")
	WebElement productNames1;

	@FindBy(xpath = "//a[@class='text-ellipsis-2']")
	List<WebElement> productNames;

	@FindBy(xpath = "//span[@class='price-new']")
	List<WebElement> productPrice;

	@FindBy(xpath = "(//a[@class='page-link'][contains(.,'>')])[1]")
	WebElement rightButton;

	@FindBy(xpath = "//a[contains(.,'|<')]")
	WebElement leftButton;

	@FindBy(xpath = "//span[.='Add to Cart']")
	WebElement cartButton;

	@FindBy(xpath="//button[@data-dismiss='toast']")
	WebElement crossbutton;
	
	public void productPage() {

		waitforWebElement(megamenu);
		ac = new Actions(driver);
		ac.moveToElement(megamenu).build().perform();
		apple.click();
	}

	public void productDetails() {

		waitforWebElement(productNames1);

		int j = 1;
		do {
			List<String> names = productNames.stream().map(a -> a.getText()).collect(Collectors.toList());
			// names.forEach(d -> System.out.println(d));
			List<String> price = productPrice.stream().map(w -> w.getText()).collect(Collectors.toList());

			System.out.println("Names\tPrice");
			for (int i = 0; i < Math.min(names.size(), price.size()); i++) {
				System.out.println(names.get(i) + "--" + price.get(i));
			}

			System.out.println("****************above details are " + j + "'st page****************");

			if (j == 3) {
				break; // i broke this loop at j=3 because the xpath is not appearing when it is in 3rd
						// page

			}
			rightButton.click();

			waitforWebElement(productNames1);
			j++;
		} while (j < 4);

		leftButton.click();

	}

	public void addtocart(String[] addtocartProd) throws InterruptedException {

		waitforWebElement(productNames1);

		for (int i = 0; i < addtocartProd.length; i++) {

			String k = addtocartProd[i];
			WebElement p = productNames.stream().filter(w -> w.getText().equalsIgnoreCase(k)).findFirst()
					.orElse(null);
			WebElement cart = p.findElement(By
					.xpath("parent::h4/parent::div/parent::div/div[1]/div[2]/button/i[@class='fas fa-shopping-cart']"));

			Thread.sleep(2000);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", cart);
		}
	}
	
	public void remove(String[] addtocartProd) throws InterruptedException {
		
		for(int i=0;i<addtocartProd.length;i++) {
		Thread.sleep(2000);
		crossbutton.click();
		}
	}

}
