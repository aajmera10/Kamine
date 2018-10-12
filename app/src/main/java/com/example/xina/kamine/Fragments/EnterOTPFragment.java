package com.example.xina.kamine.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xina.kamine.Model.SignUpDetailModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;
import com.goodiebag.pinview.Pinview;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EnterOTPFragment extends Fragment {
    ConstraintLayout submit;
    TextView usr_no;
   // private OtpView otpView;
    private Pinview pv;

    String OTP_firstname,OTP_lastname,OTP_gender,OTP_dob,OTP_mobile,OTP_email,OTP_password,pinValue;

    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.submit_otp,container,false);

        pv = view.findViewById(R.id.pinview);
        submit = view.findViewById(R.id.sub_otp);
        usr_no = view.findViewById(R.id.otp_no);
       // pinValue = pv.getValue();
        /*sp=getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        OTP_firstname = sp.getString("firstName_signup",null);
        OTP_lastname = sp.getString("lastname_signup",null);
        OTP_gender = sp.getString("gender_signup",null);
        OTP_dob = sp.getString("dob_signup",null);
        OTP_mobile =sp.getString("mobile_signup",null);
        OTP_email = sp.getString("email_signup",null);
        OTP_password = sp.getString("password_signup",null);*/
        usr_no.setText(OTP_mobile);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp=getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                OTP_firstname = sp.getString("firstName_signup",null);
                OTP_lastname = sp.getString("lastname_signup",null);
                OTP_gender = sp.getString("gender_signup",null);
                OTP_dob = sp.getString("dob_signup",null);
                OTP_mobile =sp.getString("mobile_signup",null);
                OTP_email = sp.getString("email_signup",null);
                OTP_password = sp.getString("password_signup",null);
                pinValue = pv.getValue();

                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setTitle("Loading...");
                progressDialog.show();

                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<SignUpDetailModel>signUpDetailModelCall = apiInterface.getSignupDetail(OTP_firstname,OTP_lastname,
                        OTP_gender,OTP_email,OTP_mobile,OTP_dob,OTP_password,pinValue);
                signUpDetailModelCall.enqueue(new Callback<SignUpDetailModel>() {
                    @Override
                    public void onResponse(Call<SignUpDetailModel> call, Response<SignUpDetailModel> response) {
                       if(response.body().getSuccess()==200){
                           removefragment(new LoginScreen());
                           Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();

                       }
                       if(response.body().getSuccess()==201){
                           Toast.makeText(getContext(), "Oops", Toast.LENGTH_SHORT).show();
                       }

                    }

                    @Override
                    public void onFailure(Call<SignUpDetailModel> call, Throwable t) {
                        Toast.makeText(getContext(), "Doobara Start Karo..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


















        return view;
    }

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
