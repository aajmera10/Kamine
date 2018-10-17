package com.example.xina.kamine.Utils;

import com.example.xina.kamine.Model.ChangePswdModel;
import com.example.xina.kamine.Model.ForgotPasswordModel;
import com.example.xina.kamine.Model.LoginModel;
import com.example.xina.kamine.Model.LogoutModel;
import com.example.xina.kamine.Model.MainHomeCategoryListModel;
import com.example.xina.kamine.Model.SendOTPModel;
import com.example.xina.kamine.Model.SignUpDetailModel;
import com.example.xina.kamine.Model.SocialLoginModel;
import com.example.xina.kamine.Model.UpadteProfileModel;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

  @POST("login.php")
  @FormUrlEncoded
  Call<LoginModel> getLogin(@Field("password") String password,
                            @Field("user") String user);

  @POST("send_otp.php")
  @FormUrlEncoded
  Call<SendOTPModel> getSendOTPDetail(@Field("mobile") String MobileNo);

  @POST("signup.php")
  @FormUrlEncoded
  Call<SignUpDetailModel>getSignupDetail(@Field("firstname")String Firstname,
                                         @Field("lastname") String Lastname,
                                         @Field("gender") String Gender,
                                         @Field("email")String Email,
                                         @Field("mobile")String Mobile,
                                         @Field("dob")String DOB,
                                         @Field("password")String Password,
                                         @Field("otp")String OTP);

  @POST("change_password.php")
  @FormUrlEncoded
  Call<ChangePswdModel> getChangePswd(@Field("userid") String useridStr,
                                      @Field("old_password") String oldPswdStr,
                                      @Field("new_password") String newPswdStr);

  @POST("show_category.php")
  @FormUrlEncoded
  Call<MainHomeCategoryListModel>getMainHomeCategory(@Field("id") String id,
                                                     @Field("name") String ItemName);

  @POST("logout.php")
  @FormUrlEncoded
  Call<LogoutModel>getlogout(@Field("userid") String UserId);

  @POST("forgot_password.php")
  @FormUrlEncoded
  Call<ForgotPasswordModel>getForgotModel(@Field("email") String mail);

  @POST("update_profile.php")
  @FormUrlEncoded
  Call<UpadteProfileModel>getUpdateProfie(@Field("id")String id,
                                          @Field("firstname") String firstname,
                                          @Field("lastname") String lastname,
                                          @Field("gender")String gender,
                                          @Field("email")String Email,
                                          @Field("mobile")String mobile,
                                          @Field("dob")String dob);
  @POST("social_login.php")
  @FormUrlEncoded
  Call<SocialLoginModel>getsociallogin(@Field("name")String name,
                                       @Field("mobile")String mobile,
                                       @Field("gender")String gender,
                                       @Field("dob")String dob,
                                       @Field("email")String email);


}
