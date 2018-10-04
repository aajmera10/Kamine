package com.example.xina.kamine.Model;

import com.google.gson.annotations.SerializedName;

public class SendOTPModel{

	@SerializedName("success")
	private int success;

	@SerializedName("sendOTPDetail")
	private SendOTPDetail sendOTPDetail;

	@SerializedName("message")
	private String message;

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setSendOTPDetail(SendOTPDetail sendOTPDetail){
		this.sendOTPDetail = sendOTPDetail;
	}

	public SendOTPDetail getSendOTPDetail(){
		return sendOTPDetail;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}