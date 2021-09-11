package assignments.week5.day2.assignment2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseServiceIncidentClass {
	public ChromeDriver driver;
	//public String incidentNumber;
	
	@Parameters({"url","uName","pwd","searchVal"})
	@BeforeMethod
	public void preCondition(String urlVal, String userName, String password, String searchValue) {
		
		System.out.println("Launch ServiceNow application");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(urlVal);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Login with valid credentials
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("user_name")).sendKeys(userName);
		driver.findElement(By.id("user_password")).sendKeys(password);
		driver.findElement(By.id("sysverb_login")).click();
		

		// Enter Incident in filter navigator and press enter
		driver.findElement(By.id("filter")).sendKeys(searchValue);
		driver.findElement(By.id("filter")).sendKeys(Keys.ENTER);
		

	}
	
	@AfterMethod
	public void postCondition() {
		System.out.println("Closing ServiceNow application");
		driver.close();

	}
	
	
	
	

}
