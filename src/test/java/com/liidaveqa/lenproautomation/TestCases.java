package com.liidaveqa.lenproautomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.liidaveq.utility.BrowserInit;
import com.liidaveq.pageobjects.DashboardPage;
import com.liidaveq.pageobjects.LeadPage;
import com.liidaveq.pageobjects.LoginPage;

public class TestCases {

	BrowserInit browser;
	FileInputStream inputStream;
	XSSFWorkbook work_book;
	XSSFSheet excel_sheet;
	String APP_URL, BROWSER;

	@Before
	public void setUp() throws IOException {

		String path = System.getProperty("user.dir");

		File file = new File(path + "\\src\\main\\resources\\testdata.xlsx");
		inputStream = new FileInputStream(file);
		work_book = new XSSFWorkbook(inputStream);
		excel_sheet = work_book.getSheet("browser_configuration");
		BROWSER = excel_sheet.getRow(1).getCell(0).getStringCellValue();
		APP_URL = excel_sheet.getRow(1).getCell(1).getStringCellValue();

		browser = new BrowserInit(BROWSER);
	}

	@After
	public void tearDown() {
		browser.quit();
	}

	@Test
	public void ExecuteTest() throws IOException {
		
		LeadPage newLeadEntry;
		String userName = excel_sheet.getRow(1).getCell(2).getStringCellValue();
		String password = excel_sheet.getRow(1).getCell(3).getStringCellValue();
		
		browser.get(APP_URL);
		LoginPage signInPage = new LoginPage(browser);

		signInPage.NavigateToSignInPage();
		signInPage.FillLoginForm(userName, password);
		DashboardPage userDashboard = signInPage.ClickLoginButton();
		newLeadEntry = userDashboard.NavigateToLeadPage();

		excel_sheet = work_book.getSheet("test_data");
		int rowCount = excel_sheet.getLastRowNum() - excel_sheet.getFirstRowNum();
		for(int i=1;i<=rowCount;i++) {
			newLeadEntry.ClickNewLead();
			newLeadEntry.setFirstName(excel_sheet.getRow(i).getCell(0).getStringCellValue());
			newLeadEntry.setLastName(excel_sheet.getRow(i).getCell(1).getStringCellValue());
			newLeadEntry.setPhoneNumber(excel_sheet.getRow(i).getCell(2).getStringCellValue());
			newLeadEntry.setEmail(excel_sheet.getRow(i).getCell(3).getStringCellValue());
			Date sch = excel_sheet.getRow(i).getCell(4).getDateCellValue();
			Date stm = excel_sheet.getRow(i).getCell(5).getDateCellValue();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			DateFormat tmf = new SimpleDateFormat("hh:mm aa");
//			newLeadEntry.setSchedulingDate(df.format(sch).toString());
			newLeadEntry.setSchedulingTime(tmf.format(stm).toString().toUpperCase());
//			newLeadEntry.setSchedulingDate(excel_sheet.getRow(i).getCell(4).getStringCellValue());
			System.out.println(df.format(sch).toString());
			System.out.println(tmf.format(stm).toString().toUpperCase());
//			newLeadEntry.setSchedulingTime(excel_sheet.getRow(i).getCell(5).getStringCellValue());
//			newLeadEntry.saveLeadProposal();
		}
		
		work_book.close();
		inputStream.close();
		userDashboard.ClickLogout();
	}

}
