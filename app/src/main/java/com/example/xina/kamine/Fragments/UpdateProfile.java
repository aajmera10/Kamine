package com.example.xina.kamine.Fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xina.kamine.Activities.MainHomeActivity;
import com.example.xina.kamine.Model.UpadteProfileModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile extends Fragment {

    EditText fname,lname,dob,email,mobile;
    RadioGroup group;
    TextView forgot;
    ConstraintLayout submit;
    DatePickerDialog datePickerDialog;
    SharedPreferences sharedPreferences,sp;
    RadioButton button_male,button_female;
    String gender,first_name,last_name,email_str,psswd,dateofbirth,signupID,gen,phone;

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

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removefragment(new ChangePassword());
            }
        });

        sharedPreferences = getActivity().getSharedPreferences("pref",0);
        fname.setText(sharedPreferences.getString("globalname",null));
        lname.setText(sharedPreferences.getString("globalLname",null));
        dob.setText(sharedPreferences.getString("globaldob",null));
        email.setText(sharedPreferences.getString("globalemail",null));
        mobile.setText(sharedPreferences.getString("globalMobile",null));
        gen = sharedPreferences.getString("globalgender",null);
        sharedPreferences.getString("globalD",null);


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
            }
        });
        /*int gen = group.getCheckedRadioButtonId();

        if(gen==R.id.rd_upfemale){
            gender="male";
        }else{
            gender="female";
        }*/
        /*dob.setText(sharedPreferences.getInt("",null));
        email.setText(sharedPreferences.getInt("",null));
        mobile.setText(sharedPreferences.getInt("globalMobile", Integer.parseInt(null)));*/
       // group.setText(sharedPreferences.getInt("",null));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*sharedPreferences = getActivity().getSharedPreferences("pref",0);
                fname.setText(sharedPreferences.getString("globalname",null));
                lname.setText(sharedPreferences.getString("globalLname",null));
                dob.setText(sharedPreferences.getString("globaldob",null));
                email.setText(sharedPreferences.getString("globalemail",null));
                mobile.setText(sharedPreferences.getString("globalMobile",null));*/
                gen = sharedPreferences.getString("globalgender",null);
                signupID = sharedPreferences.getString("globalD",null);

                first_name = fname.getText().toString().trim();
                last_name = lname.getText().toString().trim();
                dateofbirth = dob.getText().toString().trim();
                email_str = email.getText().toString().trim();
                phone = mobile.getText().toString().trim();

                int gendr = group.getCheckedRadioButtonId();
                if(gendr==R.id.rd_upfemale){
                    gender="male";
                }else{
                    gender="female";
                }



                if (first_name.isEmpty()) {
                    fname.setError("Please Fill Your Name");


                }else if(last_name.isEmpty()){
                    lname.setError("Please Fill Your Name");

                } else if (phone.isEmpty()) {

                    mobile.setError("Please Fill Your Phone Number");
                }

                else if (phone.length() != 10 && phone.length() != 0) {

                    mobile.setError("please Enter 10 Digits");
                }
                for (int i = 0; i < phone.length(); i++) {
                    char c = phone.charAt(i);
                    if (!(c >= '0' && c <= '9')) {

                        mobile.setError("Please enter a valid Number");
                    }
                }

                for (int i = 0; i < first_name.length(); i++) {
                    char c = first_name.charAt(i);

                    if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == ' '))) {

                        fname.setError("Please enter a valid name");
                    }
                }
                for (int i = 0; i < last_name.length(); i++) {
                    char c = last_name.charAt(i);

                    if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == ' '))) {

                        lname.setError("Please enter a valid name");
                    }
                }

              /*  sp = getActivity().getSharedPreferences("pref",0);
                sp.getString("globalname",null);
                lname.setText(sharedPreferences.getString("globalLname",null));
                dob.setText(sharedPreferences.getString("globaldob",null));
                email.setText(sharedPreferences.getString("globalemail",null));
                mobile.setText(sharedPreferences.getString("globalMobile",null));
                sharedPreferences.getString("globalD",null);*/
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setTitle("Loading...");
                progressDialog.show();

                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<UpadteProfileModel>call = apiInterface.getUpdateProfie(signupID,first_name,last_name,gender,email_str,phone,dateofbirth);
                call.enqueue(new Callback<UpadteProfileModel>() {
                    @Override
                    public void onResponse(Call<UpadteProfileModel> call, Response<UpadteProfileModel> response) {
                        if (response.body().getSuccess()==200){
                            progressDialog.dismiss();

                            sp = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                            SharedPreferences.Editor eg = sp.edit();
                            eg.putString("globalname",first_name);
                            eg.putString("globaldob",last_name);
                            eg.putString("globalgender",dateofbirth);
                            eg.putString("globalLname",last_name);
                            eg.putString("globalMobile",phone);
                            eg.putString("globalgender",gender);
                            eg.apply();

                            Toast.makeText(getContext(), "Sucessfully Updated", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpadteProfileModel> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
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
        fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit);
        fragmentTransaction.replace(R.id.frag_container, f);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        // getActivity().onBackPressed();
    }
}
