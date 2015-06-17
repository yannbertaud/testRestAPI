package test.java.testRestAPI;

import java.io.IOException;

import org.junit.Test;

import test.java.common.Common;

public class TestProducts extends Common{
	
	@Test
	public void testProductExists() throws IOException {
		println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		String url = "http://localhost:8080/products/1";
		RequestUtil util = new RequestUtil(url);

		verifyEquals(200, util.getResponseCode(), "response code");
	}

	@Test
	public void testProductDoesNotExist() throws IOException {
		println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		String url = "http://localhost:8080/products/12";
		RequestUtil util = new RequestUtil(url);

		verifyEquals(404, util.getResponseCode(), "response code");
	}
	
	@Test
	public void testFindProductByFullName() throws IOException {
		println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		String url = "http://localhost:8080/products/find?name=3ds+max+2015";
		RequestUtil util = new RequestUtil(url);

		verifyEquals(200, util.getResponseCode(), "response code");
		String expectedResponse = "[{\"description\":\"3ds max 2015 for 3d modeling, animation, rendering\",\"name\":\"3ds max 2015\",\"id\":0,\"price\":3000.0}]"; 
		verifyEquals(expectedResponse, util.getResponse(), "response");
	}
	
	@Test
	public void testFindProductByPartialNameSingleResult() throws IOException {
		println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		String url = "http://localhost:8080/products/find?name=3ds";
		RequestUtil util = new RequestUtil(url);

		verifyEquals(200, util.getResponseCode(), "response code");
		String expectedResponse = "[{\"description\":\"3ds max 2015 for 3d modeling, animation, rendering\",\"name\":\"3ds max 2015\",\"id\":0,\"price\":3000.0}]"; 
		verifyEquals(expectedResponse, util.getResponse(), "response");
	}

	@Test
	public void testFindProductByPartialNameMultipleResults() throws IOException {
		println("Starting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		String url = "http://localhost:8080/products/find?name=2015";
		RequestUtil util = new RequestUtil(url);

		verifyEquals(200, util.getResponseCode(), "response code");
		String expectedResponse = "[{\"description\":\"3ds max 2015 for 3d modeling, animation, rendering\",\"name\":\"3ds max 2015\",\"id\":0,\"price\":3000.0},{\"description\":\"software for mechanical design and simulation\",\"name\":\"Inventor 2015\",\"id\":1,\"price\":5000.0},{\"description\":\"cad software for general design\",\"name\":\"Autocad 2015\",\"id\":2,\"price\":2000.0}]"; 
		verifyEquals(expectedResponse, util.getResponse(), "response");
	}

}
