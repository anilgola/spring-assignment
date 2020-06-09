package org.anil.model.ui;

public class UserResponseModel {
	private String username;
	private String password;
	private String authorizationHeader;
	
	
	
	public String getAuthorizationHeader() {
		return authorizationHeader;
	}
	public void setAuthorizationHeader(String authorizationHeader) {
		this.authorizationHeader = authorizationHeader;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
