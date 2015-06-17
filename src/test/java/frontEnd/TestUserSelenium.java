package test.java.frontEnd;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
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
		 println("\nStarting test " + new Object(){}.getClass().getEnclosingMethod().getName());
	     getPage("http://localhost:8080/admin/user/users.html");
	     List<WebElement> rows = driver.findElementsByClassName("item");
	     verifyEquals(4, rows.size(), "row count");
	     
	     // TODO verify user list by comparing it to the list from the rest api
	     for (WebElement row: rows) {
	    	 
	     }
	 }
	 
	 @Test
	 public void verifyEditSingleUser() {
		 println("\nStarting test " + new Object(){}.getClass().getEnclosingMethod().getName());
	     getPage("http://localhost:8080/admin/user/editUser?id=1");
	     
	     // TODO change user email address and submit
	     
	     // TODO verify user has new email address both in front end and in rest api
	     
	     
	     
	 }
	 
}
