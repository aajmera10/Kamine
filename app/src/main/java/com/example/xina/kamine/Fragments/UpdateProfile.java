package com.example.xina.kamine.Fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xina.kamine.Activities.MainHomeActivity;
import com.example.xina.kamine.Model.SendOTPModel;
import com.example.xina.kamine.Model.UpadteProfileModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.VIBRATOR_SERVICE;
import static com.facebook.FacebookSdk.getApplicationContext;

public class UpdateProfile extends Fragment {

    EditText fname,lname,dob,email,mobile;
    RadioGroup group;
    TextView forgot;
    Vibrator vibrate;
    Animation shake;
    ConstraintLayout submit;
    DatePickerDialog datePickerDialog;
    SharedPreferences sharedPreferences,sp;
    RadioButton button_male,button_female;
    private ProgressDialog mProgressDialog;
    String gender,first_name,last_name,email_str,dateofbirth,signupID,gen,phone,phonechange;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_profile,container,false);
        ((MainHomeActivity)getActivity()).removeBottom();
        fname = view.findViewById(R.id.up_name);
        lname = view.findViewById(R.id.up_lname);
        dob = view.findViewById(R.id.up_dob);
        email = view.findViewById(R.id.up_email);
        mobile=view.findViewById(R.id.up_mobile);
        group = view.findViewById(R.id.up_rdgrp);
        button_female = view.findViewById(R.id.radup_woman);
        button_male = view.findViewById(R.id.rd_upfemale);
        submit = view.findViewById(R.id.sub_updt);
        forgot = view.findViewById(R.id.cpsswd);

        final Vibrator vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
        final long[] pattern = {0,400};
        first_name = fname.getText().toString().trim();
        last_name = lname.getText().toString().trim();
        dateofbirth = dob.getText().toString().trim();
        email_str = email.getText().toString().trim();
        phone = mobile.getText().toString().trim();






        sharedPreferences = getActivity().getSharedPreferences("pref",0);
        if(sharedPreferences.getBoolean("hasgooglelogin",true)
                ||sharedPreferences.getBoolean("hasfaceblogin",true)){
            forgot.setVisibility(View.GONE);
        }
        fname.setText(sharedPreferences.getString("globalname",""));
        lname.setText(sharedPreferences.getString("globalLname",""));
        dob.setText(sharedPreferences.getString("globaldob",""));
        email.setText(sharedPreferences.getString("globalemail",""));
        mobile.setText(sharedPreferences.getString("globalMobile",""));
        gen = sharedPreferences.getString("globalgender","");
        sharedPreferences.getString("globalD","");

            if (gen.equals("male")){
                button_male.setChecked(true);
            }else if (gen.equals("female")){
                button_female.setChecked(true);
                }

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dob.setText(dayOfMonth + "/"
                                + (month+1)+"/"+year);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                //datePickerDialog.getDatePicker().getMinDate();
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removefragment(new ChangePassword());
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                gen = sharedPreferences.getString("globalgender","");
                signupID = sharedPreferences.getString("globalD","");

                first_name = fname.getText().toString().trim();
                last_name = lname.getText().toString().trim();
                dateofbirth = dob.getText().toString().trim();
                email_str = email.getText().toString().trim();
                phone = mobile.getText().toString().trim();

                mobile.setText(sharedPreferences.getString("globalMobile",""));
                if(phone.equals(sharedPreferences.getString("globalMobile",""))){
                    sharedPreferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor eg = sharedPreferences.edit();
                    eg.putBoolean("phonechanged",false);
                    eg.apply();
                }else {
                    sharedPreferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor eg = sharedPreferences.edit();
                    eg.putBoolean("phonechanged",true);
                    eg.apply();

                }

                int gendr = group.getCheckedRadioButtonId();
                if(gendr==R.id.rd_upfemale){
                    gender="male";
                }else{
                    gender="female";
                }

                if(first_name.isEmpty()){
                    vibrator.vibrate(pattern, -1);
                    shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    fname.startAnimation(shake);
                    fname.setError("Please Enter the name");
                }
                else if(last_name.isEmpty()){
                    vibrator.vibrate(pattern, -1);
                    shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    lname.startAnimation(shake);
                    lname.setError("Please Enter the Last Name");
                }
                else if(dateofbirth.isEmpty()){
                    vibrator.vibrate(pattern, -1);
                    shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    dob.startAnimation(shake);
                    dob.setError("Please Enter your Date of Birth");
                }

                else if(phone.length()!=10){
                    vibrator.vibrate(pattern, -1);
                    shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    mobile.startAnimation(shake);
                    mobile.setError("Please Enter the Mobile Number");
                }
                else if(gender.isEmpty()){
                    vibrator.vibrate(pattern, -1);
                    shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    Toast.makeText(getContext(), "Please Select your Gender", Toast.LENGTH_SHORT).show();
                }




           /* if(fname.getText().toString().trim().isEmpty()||lname.getText().toString().trim().isEmpty()||dob.getText().toString().isEmpty()
                    ||email.getText().toString().isEmpty()||mobile.getText().toString().isEmpty()||mobile.getText().toString().length() != 10
                    || mobile.getText().toString().length() != 0||!Patterns.EMAIL_ADDRESS.matcher(email_str).matches()||Patterns.PHONE.matcher(phone).matches()
                    || Patterns.PHONE.matcher(phonechange).matches()){
                            Toast.makeText(getContext(), "Please Fill in The Required Fields", Toast.LENGTH_SHORT).show();
                } */else {

                if(sharedPreferences.getBoolean("phonechanged",true)){
                    showProgressDialog();
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<SendOTPModel>call = apiInterface.getSendOTPDetail(phone);
                    call.enqueue(new Callback<SendOTPModel>() {
                        @Override
                        public void onResponse(Call<SendOTPModel> call, Response<SendOTPModel> response) {
                            if (response.body().getSuccess()==200){
                                removefragment(new UpdateProfileOTP());
                                // progressDialog.dismiss();
                                hideProgressDialog();
                                sp = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                                SharedPreferences.Editor eg = sp.edit();
                                eg.putString("globalname",first_name);
                                eg.putString("globaldob",dateofbirth);
                                eg.putString("globalLname",last_name);
                                eg.putString("globalMobile",phone);
                                eg.putString("globalgender",gender);
                                eg.apply();

                                // Toast.makeText(getContext(), "Sucessfully Updated", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SendOTPModel> call, Throwable t) {
                            // progressDialog.dismiss();
                            hideProgressDialog();
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    }else{

                    showProgressDialog();
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<UpadteProfileModel>call = apiInterface.getUpdateProfie(signupID,first_name,last_name,gender,email_str,phone,dateofbirth);
                    call.enqueue(new Callback<UpadteProfileModel>() {
                        @Override
                        public void onResponse(Call<UpadteProfileModel> call, Response<UpadteProfileModel> response) {
                            if (response.body().getSuccess()==200){

                                // progressDialog.dismiss();
                                hideProgressDialog();
                                sp = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                                SharedPreferences.Editor eg = sp.edit();
                                eg.putString("globalname",first_name);
                                eg.putString("globaldob",dateofbirth);
                                eg.putString("globalLname",last_name);
                                eg.putString("globalMobile",phone);
                                eg.putString("globalgender",gender);
                                eg.apply();

                                Toast.makeText(getContext(), "Sucessfully Updated", Toast.LENGTH_SHORT).show();
                            }

                            removefragment(new AccountFragment());
                        }

                        @Override
                        public void onFailure(Call<UpadteProfileModel> call, Throwable t) {

                            // progressDialog.dismiss();
                            hideProgressDialog();
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

               }

            }
        });


        return view;
    }



    void removefragment(android.support.v4.app.Fragment f){
        //MainActivity mainActivity = new MainActivity();r
        // String c = String.valueOf(mainActivity.bottomNavigationView.getMenu());
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        // FragmentTransaction fragmentTransaction = getActivity().getSupp().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit);
        fragmentTransaction.replace(R.id.frag_container, f);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        // getActivity().onBackPressed();
    }
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("loading...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }


    }
}
