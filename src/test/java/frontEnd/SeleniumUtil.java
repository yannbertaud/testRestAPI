package test.java.frontEnd;

import static org.junit.Assert.*;
import static test.java.frontEnd.SeleniumUtil.driver;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import test.java.common.Common;

public class SeleniumUtil extends Common{
	protected static FirefoxDriver driver;
	WebElement element;

	@BeforeClass
	public static void openBrowser(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	} 
	@AfterClass
	public static void closeBrowser(){
		driver.quit();
	}
	
	protected void getPage(String url) {
		println("Opening page: " + url);
		driver.get(url);
	}
	

}
