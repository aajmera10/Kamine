package com.example.xina.kamine.Model;

import com.google.gson.annotations.SerializedName;

public class LoginModel{

	@SerializedName("success")
	private int success;

	@SerializedName("detail")
	private LoginDetail loginDetail;

	@SerializedName("message")
	private String message;

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setLoginDetail(LoginDetail loginDetail){
		this.loginDetail = loginDetail;
	}

	public LoginDetail getLoginDetail(){
		return loginDetail;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}