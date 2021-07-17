package keyDrivenFrameWork.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import keyDrivenFrameWork.TestCase.BasePageClass;

public class KeyDrivenEngine {

	public WebDriver driver;
	public final String keyFile="./KeyDrivenData/KeyDriven.xlsx";
	public static FileInputStream fi;
	public static Workbook book;
	public static Sheet sheet;
	String locatorName=null;
	String locatorValue=null;
	public BasePageClass base;
	public ReadConfig read;
	public WebElement element;
	
	public void startRead(String sheetName) {

		try {
			fi=new FileInputStream(keyFile);
		} catch (FileNotFoundException e) {
			System.out.println("Exception is"+ e.getMessage());
		}

		try {
			book=WorkbookFactory.create(fi);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet=book.getSheet(sheetName);
		int k=0;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {
				String locatorType=sheet.getRow(i+1).getCell(k+1).toString().trim();
				String locatorValue=sheet.getRow(i+1).getCell(k+2).toString().trim();
				String action=sheet.getRow(i+1).getCell(k+3).toString().trim();//Read Action column
				String data=sheet.getRow(i+1).getCell(k+4).toString().trim();//Read Data column

				switch (action) {
				case "openBrowser":
					base=new BasePageClass();
					driver=base.init_driver(data);
					break;

				case "launchURL":
					driver.get(data);
					break;
				case "quit":
					driver.quit();
					break;
				default:
					break;
				}
				switch (locatorType) {
				case "name":
					element=driver.findElement(By.name(locatorValue));
					if(action.equalsIgnoreCase("sendKeys")) {
						element.sendKeys(data);
					}else if (action.equalsIgnoreCase("click")) {
						element.click();
					}else if (action.equalsIgnoreCase("select")) {
						Select dropdown=new Select(element);
						dropdown.selectByValue(data);
					}else 	if(action.equalsIgnoreCase("isDisplayed")) {
						boolean s=element.isDisplayed();
						if (s==true) {
							System.out.println("Element Displayed");	
						}
					}
					break;
				case "xpath":
					element=driver.findElement(By.xpath(locatorValue));
					if(action.equalsIgnoreCase("isDisplayed")) {
						boolean s=element.isDisplayed();
						if (s==true) {
							System.out.println("Element Displayed");	
						}
					}else 	if(action.equalsIgnoreCase("sendKeys")) {
						element.sendKeys(data);
					}else if (action.equalsIgnoreCase("click")) {
						element.click();
					}else if (action.equalsIgnoreCase("select")) {
						Select dropdown=new Select(element);
						dropdown.selectByValue(data);
					}
					break;
				default:
					break;
				}

			}catch (Exception e) {

			}

		}
	}
}


