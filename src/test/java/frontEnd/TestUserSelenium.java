package test.java.frontEnd;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestUserSelenium extends SeleniumUtil{
	
	 @Test
	 public void verifyUsersPage(){
	
		 println("\nStarting test " + new Object(){}.getClass().getEnclosingMethod().getName());
	     getPage("http://localhost:8080/admin/user/editUser.html");
	     verifyTrue(driver.findElementByName("firstName").isDisplayed(), "firstName field is displayed");
	     verifyTrue(driver.findElementByName("lastName").isDisplayed(), "lastName field is displayed");
	     verifyTrue(driver.findElementByName("email").isDisplayed(), "email field is displayed");
	 }
	 
	 @Test
	 public void verifyGetUsersList(){
		 
	 }
	 
}
