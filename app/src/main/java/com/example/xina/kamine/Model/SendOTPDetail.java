package com.example.xina.kamine.Model;

import com.google.gson.annotations.SerializedName;

public class SendOTPDetail {

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("otp")
	private String otp;

	@SerializedName("otp_result")
	private String otpResult;

	@SerializedName("id")
	private String id;

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setOtp(String otp){
		this.otp = otp;
	}

	public String getOtp(){
		return otp;
	}

	public void setOtpResult(String otpResult){
		this.otpResult = otpResult;
	}

	public String getOtpResult(){
		return otpResult;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}