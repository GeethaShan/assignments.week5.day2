package assignments.week5.day2.assignment2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewIncident extends BaseServiceIncidentClass {
	

	@Test(dataProvider = "sendData")
	public void CreateNewServiceIncident(String callerNameVal, String shortDesc, String searchlink) throws InterruptedException {

		// Click on Create new option and fill the mandatory fields
		System.out.println("Testcase - Create new ServiceNow incident");
		driver.findElement(By.xpath("//div[text()='Watched Incidents']/following::div[text()='Create New'][1]"))
				.click();
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("lookup.incident.caller_id")).click();

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		Thread.sleep(3000);

		driver.switchTo().window(windowHandlesList.get(1));

		// Caller name
		WebElement callerName = driver.findElement(By.xpath("//label[text()='Search']/preceding::select"));
		Thread.sleep(2000);
		Select callerNameSel = new Select(callerName);
		callerNameSel.selectByValue("first_name");
		Thread.sleep(2000);

		driver.findElement(
				By.xpath("//select[@class='form-control default-focus-outline']/parent::span/following::input[1]"))
				.sendKeys(callerNameVal);
		driver.findElement(
				By.xpath("//select[@class='form-control default-focus-outline']/parent::span/following::input[1]"))
				.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+searchlink+"']")).click();
		Thread.sleep(2000);
		driver.switchTo().window(windowHandlesList.get(0));
		driver.switchTo().frame("gsft_main");

		driver.findElement(By.id("incident.short_description")).sendKeys(shortDesc);
		// get the incident number
		//String incidentNumber = retIncidentNumber();
		String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		Thread.sleep(2000);
		// String incidentNumber = getIncNumber();
		System.out.println("incidentNumber is " + incidentNumber);
		driver.findElement(By.id("sysverb_insert")).click();
		Thread.sleep(2000);
		driver.switchTo().window(windowHandlesList.get(0));
		driver.switchTo().frame("gsft_main");
		Thread.sleep(2000);

		// Verify the newly created incident
		// copy the incident number and paste it in search field and enter then verify
		// the instance number created or not
		driver.findElement(By.xpath("//span[contains(text(),'Press Enter from')]/following-sibling::input"))
				.sendKeys(incidentNumber);
		driver.findElement(By.xpath("//span[contains(text(),'Press Enter from')]/following-sibling::input"))
				.sendKeys(Keys.ENTER);

		if (driver.findElement(By.xpath("//a[text()='" + incidentNumber + "']")).isDisplayed()) {
			System.out.println("PASS - New incident is created succesfully");

		} else
			System.out.println("FAIL - New incident is not created");

	}
	
	@DataProvider
	public String[][] sendData() throws IOException {
		return ReadExcel.readData("CreateIncident");
		
	}

}
