package assignments.week5.day2.assignment2;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateExistingIncident extends BaseServiceIncidentClass{
	
	
	@Test(dataProvider = "sendData")
	public void updateIncident(String incidentNumber, String urgency, String state ) throws InterruptedException  {
		//String incidentNumber="INC0000002";
		System.out.println("Testcase - Update Existing Incident");
		System.out.println("incidentNumber : "+incidentNumber);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[text()='Incidents'])[2]")).click();
		Thread.sleep(2000);
		
		System.out.println(" Search for the existing incident and click on the incident");
		// Search for the existing incident and click on the incident
		driver.switchTo().frame("gsft_main");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//span[contains(text(),'Press Enter from')]/following-sibling::input"))
				.sendKeys(incidentNumber,Keys.ENTER);
				
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='"+incidentNumber+"']")).click();
				
		
		System.out.println("Updating the incidents with Urgency as High and State as In Progress");
		//Update the incidents with Urgency as High and State as In Progress
		WebElement urgencyElement = driver.findElement(By.id("incident.urgency"));
		Select urgencyDropDown = new Select(urgencyElement);
		urgencyDropDown.selectByValue(urgency);
		Thread.sleep(2000);
		
		WebElement stateElement = driver.findElement(By.id("incident.state"));
		Select stateDropDown = new Select(stateElement);
		Thread.sleep(2000);
				
		//Click on Update
		driver.findElement(By.id("sysverb_update")).click();
		Thread.sleep(2000);
		
		System.out.println("verify if Urgency and state is updated succesfully");
		// Verify the Urgency and state 
		driver.findElement(By.xpath("//a[text()='"+incidentNumber+"']")).click();
		
		WebElement urgDrpDown = driver.findElement(By.id("incident.urgency"));
		Select urgDrpDownSel = new Select(urgDrpDown);
		String urgValue = urgDrpDownSel.getFirstSelectedOption().getText();
		if (urgValue.equals("1 - High")) {
			System.out.println("PASS - Incident is updated succesfuly with correct Urgency");
		} 
		else
			System.out.println("FAIL - Incident is not updated succesfuly with correct Urgency.Found - "+urgValue);
		WebElement stateDrpDown = driver.findElement(By.id("incident.state"));
		Select stateDrpDownSel = new Select(stateDrpDown);
		String stateValue = stateDrpDownSel.getFirstSelectedOption().getText();
		if (stateValue.equals("In Progress")) {
			System.out.println("PASS - Incident is updated succesfuly with correct State");
		} 
		else
			System.out.println("FAIL - Incident is not updated succesfuly with correct State.Found - "+stateValue);
		
	}
	
	@DataProvider
	public String[][] sendData() throws IOException {
		return ReadExcel.readData("UpdateIncident");
		
	}

}
