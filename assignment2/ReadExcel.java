package assignments.week5.day2.assignment2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static String[][] readData(String sheetName) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook("C:/Testleaf/SeleniumWS/MavenProject/data/ServiceNowDataSheet.xlsx");
		XSSFSheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		short lastCellNum = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[rowCount][lastCellNum];
		
		for (int i=1; i<=rowCount; i++) {
			//System.out.println();
			for (int j=0; j<lastCellNum; j++) {
				String stringCellValue = sheet.getRow(i).getCell(j).getStringCellValue();
				data[i-1][j] = stringCellValue;
				System.out.println(stringCellValue);
			}
		}
		wb.close();
		return data;
	}
}
