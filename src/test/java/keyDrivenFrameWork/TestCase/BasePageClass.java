package keyDrivenFrameWork.TestCase;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import keyDrivenFrameWork.utilities.ReadConfig;

public class BasePageClass {

	ReadConfig readConfig= new ReadConfig();
	public static WebDriver driver;
	
	public WebDriver init_driver(String br) {
		if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",readConfig.chromepath());
			driver = new ChromeDriver();
		}else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",readConfig.firefoxpath());
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		return driver;
	}
	
	public void captureScreenshot(WebDriver driver, String tName) throws IOException {
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		File tFile=new File("./FrameWork.Screenshots"+tName+".png");
		FileUtils.copyFile(srcFile, tFile);
		System.out.println("Screenshot is Taken");
	}
}
