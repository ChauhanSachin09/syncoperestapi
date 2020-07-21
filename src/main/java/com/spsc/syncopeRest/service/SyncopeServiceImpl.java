/**
 * 
 */
package com.spsc.syncopeRest.service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.spsc.syncopeRest.utility.HttpUtility;

/**
 * @author Sachin Pratap Singh
 *
 */
@Configuration
@PropertySource("file:src/main/resources/application.properties")
@Service
public class SyncopeServiceImpl implements SyncopeService{
	
	@Value("${authorizationUrl}")
	private String tokenUrl;
	
	@Value("${userSelfUrl}")
	private String userUrl;
	
	
	@Autowired
	HttpUtility httpUtility;
	
	
	
	public SyncopeServiceImpl() {}

	public SyncopeServiceImpl(HttpUtility httpUtility) {
		this.httpUtility = httpUtility;
	}

	@Override
	public String getAuthorizationToken(String userName, String Password) {
		String encoding = "";
		String token = "";
		try {
			encoding = Base64.getEncoder().encodeToString((userName+":"+Password).getBytes("utf-8"));
			encoding ="Basic "+encoding;
			System.out.println("Encoding ----->"+encoding);	
			System.out.println("tokenUrl ----->"+tokenUrl);	
			if (encoding!=null && !encoding.isEmpty()) {
				token=httpUtility.getAuthToken(tokenUrl, encoding);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return token;
	}

	@Override
	public String getUserData(String token) {
		// TODO Auto-generated method stub
		String encoding = "";
		String result = "";
		try {
			encoding ="Bearer "+token;
			System.out.println("Encoding ----->"+encoding);			
			if (encoding!=null && !encoding.isEmpty()) {
				result=httpUtility.getUserData(userUrl, encoding);
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
