package test.java.testRestAPI;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

import org.junit.Test;

import test.java.common.Common;

public class TestUser extends Common{

	@Test
	public void testUserExists() throws IOException {
		String url = "http://localhost:8080/users/1";
		RequestUtil util = new RequestUtil(url);
		
		verifyEquals(util.getResponseCode(), 200, "response code");
		verifyEquals(util.getResponse(), "{\"firstName\":\"yann\",\"lastName\":\"bertaud\",\"email\":null,\"password\":null,\"userId\":1}", "response");
	}
	
	@Test
	public void testUserDoesNotExist() throws IOException {
		String url = "http://localhost:8080/users/5";
		RequestUtil util = new RequestUtil(url);
		verifyEquals(util.getResponseCode(), 404, "response code");
	}
	
	@Test
	public void testCreateUser() {
		//http://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
		String url = "http://localhost:8080/users";
		String jsonInput = "{\"firstName\": \"John\", \"lastName\": \"smith\", \"email\": \"johnsmith@gmail.com\"}";
		postJSONRequest(url, jsonInput);
	}
	
	@Test
	public void testDeleteUser() {
		verifyEquals(deleteUser(1, "deleting existing user"), "202 Accepted", "delete user succeeded");

	}
	
	
	@Test
	public void testDeleteNonExistingUser() {
		
		verifyEquals(deleteUser(5, "deleting non existing user"), "404 Not Found", "delete non existing user response code");
	}

	@Test
	public void testUserAuthentication() {
		
	}
	
	// test 
	
	
	
	
	
	
	
	private String deleteUser(int userId, String message) {
		String url = "http://localhost:8080/users/delete/" + userId;
		String responseCode = null;
		HttpURLConnection conn;
		try {
			System.out.println(message);
			conn = (HttpURLConnection) (new URL(url)).openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("DELETE");
			responseCode = conn.getResponseCode() + " " + conn.getResponseMessage();
			System.out.println("\nSending 'Delete' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			return responseCode;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseCode;
	}
	
	private void postJSONRequest(String url, String jsonInput) {
		try {
			HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",  "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(jsonInput.getBytes());
			os.flush();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("failed : HTTP error code: " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			System.out.println("output from server:");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	

}
