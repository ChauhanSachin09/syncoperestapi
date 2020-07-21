/**
 * 
 */
package com.spsc.syncopeRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spsc.syncopeRest.service.SyncopeService;

/**
 * @author Sachin Pratap Singh
 *
 */
@RestController
@RequestMapping("/syncope/rest")
public class Controller {
	
	@Autowired
	SyncopeService service;

	@RequestMapping("Ping")
	@GetMapping
	public String getPing() {
		return "Status OK";		
	}
	
	@RequestMapping("/accessTokens/login")
	@PostMapping
	public String getToken(@RequestHeader("userName") String userName,@RequestHeader("passWord") String passWord ) {
		
		if ((userName !=null && !userName.isEmpty()) && (passWord!=null && !passWord.isEmpty())) {
			return service.getAuthorizationToken(userName, passWord);
		}
		return "";
		
	}
	
	@RequestMapping("/users/self")
	@PostMapping
	public String getUserData(@RequestHeader("token") String token) {
		
		if ((token !=null && !token.isEmpty())) {
			return service.getUserData(token);
		}
		return "";
		
	}
	
	@RequestMapping("/userdata")
	@PostMapping
	public String getUserDataCombo(@RequestHeader("userName") String userName,@RequestHeader("passWord") String passWord) {
		String token="";
		if ((userName !=null && !userName.isEmpty()) && (passWord!=null && !passWord.isEmpty())) {
			token= service.getAuthorizationToken(userName, passWord);
		}
		
		if (token!=null && !token.isEmpty()) {
			return service.getUserData(token);
		}
		return "";
		
		
	}
	
	
}
