package test.java.testRestAPI;

import java.io.IOException;

import org.junit.Test;

import test.java.common.Common;

public class TestEntitlements extends Common {
	String baseUrl = "http://localhost:8080/entitlements";
	
	
	@Test
	public void testEntitlementsList() throws IOException {
		println("\nStarting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		RequestUtil util = new RequestUtil(baseUrl);
		
		verifyEquals(200, util.getResponseCode(), "responseCode");
		println(util.getResponse());
	}
	
	@Test
	public void testEntitlementExists() throws IOException {
		println("\nStarting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		RequestUtil util = new RequestUtil(baseUrl + "/1");
		
		verifyEquals(200, util.getResponseCode(), "responseCode");
		println(util.getResponse());
	}
	
}
