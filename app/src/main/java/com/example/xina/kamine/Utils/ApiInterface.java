package com.example.xina.kamine.Utils;

import com.example.xina.kamine.Model.AddToWishlistModel;
import com.example.xina.kamine.Model.ChangePswdModel;
import com.example.xina.kamine.Model.ForgotPasswordModel;
import com.example.xina.kamine.Model.HomeSliderMainModel;
import com.example.xina.kamine.Model.LoginModel;
import com.example.xina.kamine.Model.LogoutModel;
import com.example.xina.kamine.Model.MainHomeCategoryListModel;
import com.example.xina.kamine.Model.OtherCategoryModel;
import com.example.xina.kamine.Model.ProductListModel;
import com.example.xina.kamine.Model.SaveAddressModel;
import com.example.xina.kamine.Model.SendOTPModel;
import com.example.xina.kamine.Model.ShowAddressesModel;
import com.example.xina.kamine.Model.SignUpDetailModel;
import com.example.xina.kamine.Model.SocialLoginModel;
import com.example.xina.kamine.Model.SubcategoryMainModel;
import com.example.xina.kamine.Model.UpadteProfileModel;
import com.example.xina.kamine.Model.UpdateProfileOTPModel;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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

  @POST("show_subcategory.php")
  @FormUrlEncoded
  Call<SubcategoryMainModel> getMainHomeSubcategory(@Field("categoryid")String id);

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

  @POST("update_profile.php")
  @FormUrlEncoded
  Call<UpdateProfileOTPModel>getUpdateProfieOTP(@Field("id")String id,
                                                @Field("firstname") String firstname,
                                                @Field("lastname") String lastname,
                                                @Field("gender")String gender,
                                                @Field("email")String Email,
                                                @Field("dob")String dob,
                                                @Field("mobile")String mobile,
                                                @Field("otp")String OTP);


  @POST("slider.php")
  @FormUrlEncoded
  Call<HomeSliderMainModel>getMainHomeSlider(@Field("id")String id);


  @POST("saved_address.php")
  @FormUrlEncoded
  Call<SaveAddressModel>getSavedAddress(@Field("userid")String id,
                                        @Field("firstname")String firstname,
                                        @Field("lastname")String lastname,
                                        @Field("address")String address,
                                        @Field("landmark")String landmark,
                                        @Field("mobile")String mobile,
                                        @Field("pincode")String pincode,
                                        @Field("city")String city,
                                        @Field("state")String state,
                                        @Field("country")String country);

  @POST("product_list.php")
  @FormUrlEncoded
  Call<ProductListModel>getProductList(@Field("sub_categoryid")String Category_ID);

  @POST("other_category.php")
  @FormUrlEncoded
  Call<OtherCategoryModel>getothercategory(@Field("id") String id);


  @POST("wishlist.php")
  @FormUrlEncoded
  Call<AddToWishlistModel>getaddtowishlist(@Field("userid")String id,
                                        @Field("productid")String productid);

  @POST("show_address.php")
  @FormUrlEncoded
  Call<ShowAddressesModel>getshowaddresses(@Field("userid") String id);



}
