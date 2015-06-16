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

public class TestUser {

	@Test
	public void testUserExists() throws IOException {
		String url = "http://localhost:8080/users/1";
		RequestUtil util = new RequestUtil(url);
		
		assertEquals(util.getResponseCode(), 200);
		assertEquals(util.getResponse(), "{\"firstName\":\"yann\",\"lastName\":\"bertaud\",\"email\":null,\"password\":null,\"userId\":1}");
	}
	
	@Test
	public void testUserDoesNotExist() throws IOException {
		String url = "http://localhost:8080/users/5";
		RequestUtil util = new RequestUtil(url);
		assertEquals(util.getResponseCode(), 404);
	}
	
//	@Test
//	public void testCreateUser() throws IOException {
//		String url = "http://localhost:8080/users/5";
//		RequestUtil util = new RequestUtil(url, "POST");
//		assertEquals(util.getResponseCode(), 405);
//		assertEquals(util.getResponse(), null);
//	}
//	
//	@Test
//	public void testUpdateUser() throws IOException {
//		String url = "http://localhost:8080/users/5";
//		RequestUtil util = new RequestUtil(url, "PUT");
//		assertEquals(util.getResponseCode(), 405);
//		assertEquals(util.getResponse(), null);
//	}
	
	@Test
	public void testCreateUser() {
		//http://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
		String url = "http://localhost:8080/users";
		String jsonInput = "{\"firstName\": \"John\", \"lastName\": \"smith\", \"email\": \"johnsmith@gmail.com\"}";
		postJSONRequest(url, jsonInput);
	}
	
	@Test
	public void testDeleteUser() {
		int userId = 1;
		assertEquals(deleteUser(userId), "202 Accepted");

	}
	
	@Test
	public void testDeleteNonExistingUser() {
		int userId = 5;
		assertEquals(deleteUser(userId), "404 Not Found");
	}

	private String deleteUser(int userId) {
		String url = "http://localhost:8080/users/delete/" + userId;
		String response = null;
		HttpURLConnection conn;
		try {
			System.out.println("deleting user");
			conn = (HttpURLConnection) (new URL(url)).openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("DELETE");
			response = conn.getResponseCode() + " " + conn.getResponseMessage();
			System.out.println(response);
			return response;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
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
