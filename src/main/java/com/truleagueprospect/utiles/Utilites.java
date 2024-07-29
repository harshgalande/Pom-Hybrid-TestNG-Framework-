package com.truleagueprospect.utiles;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilites {

	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 10;

	public static String generateEmailWithTimeStamp() {

		Date date = new Date();
		String timeStamp = date.toString().replaceAll(" ", "_").replace(":", "_");
		return "harsh+" + timeStamp + "@truleague.com";

	}

	public static WebDriverWait createWebDriverWait(WebDriver driver) {
		return new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public static Object[][] getTestDataFromExcelFile(String sheetName) {

		File excelfile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\truleagueprospect\\testdata\\TruleagueProspectTestData.xlsx");
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(excelfile);
			workbook = new XSSFWorkbook(fisExcel);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < cols; j++) {

				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch (cellType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;

				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;

				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}

		}
		return data;

	}

}