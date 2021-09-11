package assignments.week5.day2.assignment1;


import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLead extends BaseClass {
	
	@Test(dataProvider = "sendData")
	public void runCreateLead(String fName, String lName, String companyName, String phoneNum) {
		System.out.println("Starting Testcase Create Lead");
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(fName);
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lName);
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(companyName);
		driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(phoneNum);
		driver.findElement(By.name("submitButton")).click();
		System.out.println("Ending Testcase Create Lead");
}
/*
 * //Passing hard coded data using Data Provider
 * 
 * @DataProvider public String[][] sendData_Hardcoded() { String[][] data = new
 * String[2][4]; data[0][0]="Hari"; data[0][1]="R"; data[0][2]="TestLeaf";
 * data[0][3]="99";
 * 
 * data[1][0]="Sheriba"; data[1][1]="T"; data[1][2]="TestLeaf"; data[1][3]="98";
 * 
 * return data;
 * 
 * }
 */
	
	//Passing data using excel sheet in Data provider
	@DataProvider
	public String[][] sendData() throws IOException {
		return ReadExcel.readData("CreateLead");
	}
	
	
}






