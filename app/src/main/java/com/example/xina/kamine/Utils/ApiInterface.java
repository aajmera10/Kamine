package com.example.xina.kamine.Utils;

import com.example.xina.kamine.Model.LoginModel;
import com.example.xina.kamine.Model.SendOTPModel;
import com.example.xina.kamine.Model.SignUpDetailModel;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

  @POST("login.php")
  @FormUrlEncoded
  Call<LoginModel> getLogin(@Field("password") String password,
                            @Field("user") String user);
  @POST("send_otp.php")
  @FormUrlEncoded
  Call<SendOTPModel> getSendOTPDetail(@Field("mobile") String MobileNo);
  /*@POST("signup.php")
  @FormUrlEncoded
  Call<SignUpDetailModel>*/



}
