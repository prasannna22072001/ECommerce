package NewPrasanna.POM;

import java.util.ArrayList;
import java.util.Arrays;
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

public class CartPOM extends AbstractComponent {

	List<String> Names;

	public CartPOM(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//a/div[@class='cart-icon'])[1]/div")
	WebElement cartbutton;

	@FindBy(xpath = "//tr/td[2]/a")
	WebElement productNamesone;

	@FindBy(xpath = "//tr/td[2]/a")
	List<WebElement> productNames;

	@FindBy(xpath = "//tr/td[3]")
	List<WebElement> NoOfProducts;

	@FindBy(xpath = "//tr/td[4]")
	List<WebElement> CostOfProducts;

	@FindBy(xpath = "//a[@href='https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart']")
	WebElement checkout;

	public void cart(String[] addtocartProd) {

		cartbutton.click();

		waitforWebElement(productNamesone);
		Names = productNames.stream().map(q -> q.getText()).collect(Collectors.toList());
		Names.forEach(a -> System.out.println(a));

		boolean isEqualDirect = Arrays.equals(Names.toArray(), addtocartProd);
		if (isEqualDirect) {
			System.out.println("The lists are equal");
		} else {
			System.out.println("The lists are not equal");
		}
	}

	public void productsDetails() {

		List<String> Howmany = NoOfProducts.stream().map(w -> w.getText()).collect(Collectors.toList());
		List<String> cost = CostOfProducts.stream().map(w -> w.getText()).collect(Collectors.toList());

		for (int i = 0; i < Math.min(Names.size(), Howmany.size()); i++) {
			System.out.println("product name is: " + Names.get(i) + " added " + Howmany.get(i) + " product"
					+ " and cost is " + cost.get(i));
		}

		checkout.click();
	}

	public void deleteproduct(String productName) throws InterruptedException {

		// waitforWebElement(productNamesone);
		Thread.sleep(3000);
		WebElement p = productNames.stream().filter(e -> e.getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		p.findElement(By.xpath("parent::td/following-sibling::td[2]/div/div/button[2]")).click();

	}

}
