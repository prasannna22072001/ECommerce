package NewPrasanna.ECommerce;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import NewPrasanna.POM.RegisterPOM;

public class BaseTest {

	public  WebDriver driver;
	
//	public  WebDriver startDriver() throws IOException {
//
//		Properties prop = new Properties();
// 		FileInputStream fis = new FileInputStream(
// 				System.getProperty("user.dir") + "\\src\\main\\java\\NewPrasanna\\resources\\GlobalData.properties");
// 		prop.load(fis);
// 		String browserName = System.getProperty("browser") != null ? System.getProperty("broswer")
// 				: prop.getProperty("browser");
// 
// 		if (browserName.contains("chrome")) {
// 
// 			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\eclipse-workspace\\chromedriver.exe");
// 			driver = new ChromeDriver();
// 			driver.manage().window().maximize();
// 			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
// 			
// 		}else if(browserName.equalsIgnoreCase("edge")) {
// 			
// 			 //write edge driver code
// 		}else if(browserName.equalsIgnoreCase("fire fox")){
// 			// write fire fox code
//		}
//
//		
//		return driver;
//	}
	
	
	@BeforeMethod(alwaysRun=true)
	public WebDriver startDriver() {
 		
 		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\eclipse-workspace\\chromedriver.exe");
 		driver = new ChromeDriver();
 		driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
 		driver.manage().window().maximize();
 		return driver;
 		
 	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
				StandardCharsets.UTF_8);

		
	//String to HashMap- Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	
	//{map, map}

	}
 	
// 	@BeforeMethod(alwaysRun=true)
// 	public RegisterPOM launchapplication() throws IOException {
// 		
// 		driver = startDriver();
//		register = new RegisterPOM(driver);
//		register.gotopage();
// 		return register;
// 		
// 	}

 	
}
