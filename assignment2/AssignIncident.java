package assignments.week5.day2.assignment2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignIncident extends BaseServiceIncidentClass{
	
	@Test(dataProvider = "sendData")
	public void assignServiceNowIncident(String incidentNumber, String assGroup) throws InterruptedException {
		//String incidentNumber = "INC0000003";
		Thread.sleep(3000);
		System.out.println("Testcase - Assign ServiceNow Incident");
		System.out.println("incidentNumber : "+incidentNumber);
		// click on open
		driver.findElement(By.xpath("//div[text()='Open'][1]")).click();
		Thread.sleep(2000);
		
		// Search for the existing incident
		System.out.println("Search for the existing incident");
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("(//label[text()='Search'])[2]/following-sibling::input")).sendKeys(incidentNumber,Keys.ENTER);
		
		// click on the incident
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='"+incidentNumber+"']")).click();

		// Assign the incident to Software group
		System.out.println("Assign it to Software group");
		driver.findElement(By.id("lookup.incident.assignment_group")).click();
		Thread.sleep(2000);

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(1));

		driver.findElement(By.xpath("//input[@placeholder='Search'][1]")).sendKeys(assGroup, Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Software']")).click();
		Thread.sleep(2000);

		driver.switchTo().window(windowHandlesList.get(0));
		driver.switchTo().frame("gsft_main");

		// Update the incident with Work Notes
		System.out.println("Update the incident with Work Notes");
		driver.findElement(By.xpath("//textarea[@id='activity-stream-textarea']")).sendKeys("Updated comment");
		driver.findElement(By.xpath("//button[text()='Update'][1]")).click();

		// Verify the Assignment group and Assigned for the incident
		driver.findElement(By.xpath("(//label[text()='Search'])[2]/following-sibling::input")).sendKeys(incidentNumber,
				Keys.ENTER);
		Thread.sleep(2000);
		
		
		// click on the incident
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='"+incidentNumber+"']")).click();
		Thread.sleep(3000);
		
		//Verify the Assignment group is assigned for the incident
		System.out.println("Check if assignment group is updated properly");
		String assGroupText = driver.findElement(By.xpath("//input[@id='sys_display.incident.assignment_group']")).getAttribute("value");
		if (assGroupText.equals("Software")) {
			System.out.println("PASS - Incident updated with proper assignment group");
		}
		else
			System.out.println("FAIL - Incident is not updated with proper assignment group.Found : "+assGroupText);
	}
	
	@DataProvider
	public String[][] sendData() throws IOException {
		return ReadExcel.readData("AssignIncident");
	}

}
