package assignments.week5.day2.assignment1;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class EditLead extends BaseClass {
	@Test(dataProvider = "sendData")
	public void runEditLead(String phNum, String compName) throws InterruptedException {
		System.out.println("Starting Testcase Edit Lead");
		driver.findElement(By.linkText("Find Leads")).click();
		driver.findElement(By.xpath("//span[text()='Phone']")).click();
		driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(phNum);
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.findElement(By.linkText("Edit")).click();
		driver.findElement(By.id("updateLeadForm_companyName")).sendKeys(compName);
		driver.findElement(By.name("submitButton")).click();
		System.out.println("Ending Testcase Edit Lead");
}
	@DataProvider
	public String[][] sendData() throws IOException {
		return ReadExcel.readData("EditLead");
}
}






