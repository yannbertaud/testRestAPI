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

public class TestUser extends Common {

	@Test
	public void testUserExists() throws IOException {
		println("\nStarting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		String url = "http://localhost:8080/users/1";
		RequestUtil util = new RequestUtil(url);

		verifyEquals(200, util.getResponseCode(), "response code");
		verifyEquals(
				"{\"firstName\":\"andy\",\"lastName\":\"foobar\",\"email\":null,\"password\":null,\"id\":1}",
				util.getResponse(), "response");
	}

	@Test
	public void testUserDoesNotExist() throws IOException {
		println("\nStarting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		String url = "http://localhost:8080/users/5";
		RequestUtil util = new RequestUtil(url);
		verifyEquals(404, util.getResponseCode(), "response code");
	}

	@Test
	public void testCreateUser() {
		println("\nStarting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		// http://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
		String url = "http://localhost:8080/users";
		String jsonInput = "{\"firstName\": \"John\", \"lastName\": \"smith\", \"email\": \"johnsmith@gmail.com\"}";
		verifyEquals("201 Created", postJSONRequest(url, jsonInput), "user created successfully");
	}

	@Test
	public void testUpdateUser() {
		println("\nStarting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		String url = "http://localhost:8080/users";
		String jsonInput = "{\"firstName\": \"John\", \"lastName\": \"smith\", \"email\": \"johnsmith@gmail.com\"}";
		verifyEquals("202 Accepted", putJSONRequest(url, jsonInput),
				"update user");
	}

	@Test
	public void testDeleteUser() {
		println("\nStarting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		verifyEquals("202 Accepted", deleteUser(1, "deleting existing user"),
				"delete user succeeded");
	}

	@Test
	public void testDeleteNonExistingUser() {
		println("\nStarting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		verifyEquals("404 Not Found",
				deleteUser(5, "deleting non existing user"),
				"delete non existing user response code");
	}

	@Test
	public void testFindUserByFirstLastName() throws IOException {
		println("\nStarting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		String url = "http://localhost:8080/users/find?firstName=andy&lastName=foobar";
		RequestUtil util = new RequestUtil(url);

		verifyEquals(200, util.getResponseCode(), "response code");
		verifyEquals(
				"[{\"firstName\":\"andy\",\"lastName\":\"foobar\",\"email\":null,\"password\":null,\"id\":1}]",
				util.getResponse(), "response");
	}

	@Test
	public void testFindUserByFirstName() throws IOException {
		println("\nStarting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		String url = "http://localhost:8080/users/find?firstName=andy";
		RequestUtil util = new RequestUtil(url);

		verifyEquals(200, util.getResponseCode(), "response code");
		verifyEquals(
				"[{\"firstName\":\"andy\",\"lastName\":\"foobar\",\"email\":null,\"password\":null,\"id\":1},{\"firstName\":\"andy\",\"lastName\":\"smith\",\"email\":null,\"password\":null,\"id\":3}]",
				util.getResponse(), "response");
	}

	@Test
	public void testFindUserByLastName() throws IOException {
		println("\nStarting test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		String url = "http://localhost:8080/users/find?lastName=foobar";
		RequestUtil util = new RequestUtil(url);

		verifyEquals(200, util.getResponseCode(), "response code");
		verifyEquals(
				"[{\"firstName\":\"andy\",\"lastName\":\"foobar\",\"email\":null,\"password\":null,\"id\":1}]",
				util.getResponse(), "response");
	}

	// test

	private String deleteUser(int id, String message) {
		String url = "http://localhost:8080/users/delete/" + id;
		String responseCode = null;
		HttpURLConnection conn;
		try {
			println("  message -- " + message);
			conn = (HttpURLConnection) (new URL(url)).openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("DELETE");
			responseCode = conn.getResponseCode() + " "
					+ conn.getResponseMessage();
			action("Sending '" + conn.getRequestMethod()
					+ "' request to URL : " + url);
			// System.out.println("Response Code : " + responseCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseCode;
	}

	private String postJSONRequest(String url, String jsonInput) {
		String responseCode = null;
		try {
			HttpURLConnection conn = (HttpURLConnection) (new URL(url))
					.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(jsonInput.getBytes());
			os.flush();
			responseCode = conn.getResponseCode() + " "
					+ conn.getResponseMessage();
			// if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			// throw new RuntimeException("failed : HTTP error code: " +
			// conn.getResponseCode());
			// }

			action("Sending '" + conn.getRequestMethod()
					+ "' request to URL : " + url);
			// System.out.println("Response Code : " + responseCode);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String output;
			System.out.println("output from server:");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseCode;
	}

	private String putJSONRequest(String url, String jsonInput) {
		String responseCode = null;
		try {
			HttpURLConnection conn = (HttpURLConnection) (new URL(url))
					.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(jsonInput.getBytes());
			os.flush();
			responseCode = conn.getResponseCode() + " "
					+ conn.getResponseMessage();

			// if (conn.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED) {
			// throw new RuntimeException("failed : HTTP error code: " +
			// conn.getResponseCode());
			// }

			action("Sending '" + conn.getRequestMethod()
					+ "' request to URL : " + url);
			// System.out.println("Response Code : " + responseCode);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String output;
			System.out.println("output from server:");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseCode;
	}

}
