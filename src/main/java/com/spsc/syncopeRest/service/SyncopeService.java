/**
 * 
 */
package com.spsc.syncopeRest.service;

/**
 * @author Sachin Pratap Singh
 *
 */
public interface SyncopeService {
	
	public String getAuthorizationToken(String userName, String Password);
	
	public String getUserData(String token);

}
