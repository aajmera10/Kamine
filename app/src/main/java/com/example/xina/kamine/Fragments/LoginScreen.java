package com.example.xina.kamine.Fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xina.kamine.Activities.MainHomeActivity;
import com.example.xina.kamine.Model.LoginModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiInterface;
import com.example.xina.kamine.Utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class LoginScreen extends Fragment {
LinearLayout Login;
TextView signup,forgot_password;
RadioButton otp;
String password,user;
ProgressDialog pDialog;
String userName,userLName,userDOB,userGender,userMobile,userID;
SharedPreferences sp;
TextInputEditText enter_passwd,enter_mail;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_screen,container,false);
        ((MainHomeActivity)getActivity()).removeBottom();
        Login = view.findViewById(R.id.linearLayout2);
        enter_passwd = view.findViewById(R.id.enter_password);
        enter_mail = view.findViewById(R.id.enter_email);
        signup = view.findViewById(R.id.sign_up);
        otp = view.findViewById(R.id.login_otp);
        forgot_password = view.findViewById(R.id.forgot_password);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==Login)
                {
                    password = enter_passwd.getText().toString().trim();
                    user = enter_mail.getText().toString().trim();
                  /*  new ProgressDialog(getActivity());
                    pDialog.setTitle("Akshat The Designer");
                    pDialog.show();*/
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<LoginModel> call = apiInterface.getLogin(password,user);
                  //  Call<LoginModel> call = apiInterface.getLogin(password,user);
                    call.enqueue(new Callback<LoginModel>() {
                        @Override
                        public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                            if (response.body().getSuccess()==200)
                            {

                               // pDialog.dismiss();
                                Toast.makeText(getActivity(),response.toString(), Toast.LENGTH_SHORT).show();
                                userName  =  response.body().getLoginDetail().getFname();
                                userDOB =    response.body().getLoginDetail().getDob();
                                userGender = response.body().getLoginDetail().getGender();
                                userLName =  response.body().getLoginDetail().getLname();
                                userMobile = response.body().getLoginDetail().getMobile();
                                userID = response.body().getLoginDetail().getId();
                                //id=response.body().getLoginDetail().get

                                sp = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
                                SharedPreferences.Editor eg = sp.edit();
                                eg.putString("globalname",userName);
                                eg.putString("globaldob",userDOB);
                                eg.putString("globalgender",userGender);
                                eg.putString("globalLname",userLName);
                                eg.putString("globalMobile",userMobile);
                                eg.putString("globalD",userID);
                                eg.commit();
                                eg.apply();

                                removefragment(new AccountFragment());

                            }
                        }

                        @Override
                        public void onFailure(Call<LoginModel> call, Throwable t) {
                          //  pDialog.dismiss();
                            Toast.makeText(getActivity(),t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removefragment(new ForgotPassword());
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removefragment(new SignupFragment());
            }
        });
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removefragment(new GetOTP());
            }
        });

        return view;
    }



    //@SuppressLint("ResourceType")
    /*private void replaceFragment(android.app.Fragment f) {
        android.app.FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction  = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit);
        //bottomNavigationView.setVisibility(View.VISIBLE);
        transaction.replace(R.id.frag_container,f);
        transaction.addToBackStack(null);
        transaction.commit();

    */

    void removefragment(android.support.v4.app.Fragment f){
        //MainActivity mainActivity = new MainActivity();r
        // String c = String.valueOf(mainActivity.bottomNavigationView.getMenu());
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        // FragmentTransaction fragmentTransaction = getActivity().getSupp().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,R.anim.fragment_slide_right_exit);
        fragmentTransaction.replace(R.id.frag_container, f);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        // getActivity().onBackPressed();
    }
}
