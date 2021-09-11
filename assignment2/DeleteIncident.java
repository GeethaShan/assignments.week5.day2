package assignments.week5.day2.assignment2;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteIncident extends BaseServiceIncidentClass{
	
	@Test(dataProvider = "sendData")
	public void deleteServiceNowIncident(String incidentNumber) throws InterruptedException {
		//String incidentNumber = "INC0000015";
		Thread.sleep(3000);
		System.out.println("Testcase - Delete incident");
		System.out.println("incidentNumber : "+incidentNumber);
		driver.findElement(By.xpath("(//div[text()='Incidents'])[2]")).click();
		Thread.sleep(2000);

		System.out.println(" Search for the existing incident");
		// Search for the existing incident and click on the incident
		driver.switchTo().frame("gsft_main");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//span[contains(text(),'Press Enter from')]/following-sibling::input"))
				.sendKeys(incidentNumber,Keys.ENTER);
		
		Thread.sleep(2000);
		System.out.println("Navigate to the incident");
		driver.findElement(By.xpath("//a[text()='"+incidentNumber+"']")).click();
		Thread.sleep(2000);

		// Delete the incident
		driver.findElement(By.xpath("//button[text()='Delete'][1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(text(),'Delete this record')]/following::div/button[2]")).click();
		Thread.sleep(2000);

		// Verify the deleted incident
		System.out.println("verify if incident is deleted properly");
		Thread.sleep(2000);

		// Search the deleted incident
		driver.findElement(By.xpath("//span[contains(text(),'Press Enter from')]/following-sibling::input"))
				.sendKeys(incidentNumber,Keys.ENTER);
		
		if (driver.findElement(By.xpath("//td[text()='No records to display']")).isDisplayed()) {
			System.out.println("PASS - Incident is deleted properly");
		} else
			System.out.println("FAIL - Incident is not deleted properly");
	}
	
	@DataProvider
	public String[][] sendData() throws IOException {
		return ReadExcel.readData("DeleteIncident");
		
	}


}
