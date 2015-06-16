package main.java.testRestAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;

public class RequestUtil {
	private final String USER_AGENT = "Mozilla/5.0";
	
	private int responseCode;
	private StringBuffer response;

	public RequestUtil(String url) throws IOException{
		this.makeRequest(url, "GET", null);
	}
	
	public RequestUtil(String url, String requestMethod) throws IOException {
		this.makeRequest(url, requestMethod, null);
	}
	
	public RequestUtil(String url, String requestMethod, HashMap<String, String> properties) throws MalformedURLException, ProtocolException {
		this.makeRequest(url, requestMethod, properties);
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	
	public String getResponse() {
		if (response != null) {
			return response.toString();			
		} else {
			return null;
		}
	}
	
	private void makeRequest(String url, String requestMethod, HashMap<String, String> properties) throws MalformedURLException, ProtocolException  {
		 
		URL obj = new URL(url);
		HttpURLConnection con;
		try {
			con = (HttpURLConnection) obj.openConnection();
			// optional default is GET
			con.setRequestMethod(requestMethod);
	 
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
			if (properties != null) {
				for(Entry<String, String> entry: properties.entrySet()) {
					con.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
	 
			this.responseCode = con.getResponseCode();
			System.out.println("\nSending '" + requestMethod + "' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
 
 
		//print result
		if (response != null) {
			System.out.println(response.toString());					
		} else {
			System.out.println("response is null");
		}
			
	}
	
	
}
