/**
 * 
 */
package com.spsc.syncopeRest.utility;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @author Sachin Pratap Singh
 *
 */
@Service
public class HttpUtility {

	public String getAuthToken(String url, String base64String) {
		
		System.out.println("I am here---------------------?");
		
		String token = "";
		try {

			URL url1 = new URL(url);

			HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", base64String);
			InputStream content = (InputStream) connection.getInputStream();

			// BufferedReader in =
			// new BufferedReader (new InputStreamReader (content));
			Map<String, List<String>> map = connection.getHeaderFields();
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " ,Value : " + entry.getValue());
			}
			token = connection.getHeaderField("X-Syncope-Token");

			System.out.println("Token \n" + token);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;

	}
	
	public String getUserData(String url, String authString) {
		
		try {
			URL url1 = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", authString);
			InputStream content = (InputStream) connection.getInputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			String line;
            while ((line = in.readLine()) != null) {
               return (line);
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
	}

}
