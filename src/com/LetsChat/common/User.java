/**
 * This is the Object of User Information
 */

package com.LetsChat.common;

import java.io.Serializable;

public class User implements Serializable{
	private String userId;
	private String pwd;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	

}
