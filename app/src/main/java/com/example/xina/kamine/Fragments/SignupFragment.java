package com.example.xina.kamine.Fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.telecom.Call;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xina.kamine.Activities.MainHomeActivity;
import com.example.xina.kamine.Model.SendOTPModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import java.util.Calendar;

import retrofit2.Callback;
import retrofit2.Response;

public class SignupFragment extends Fragment {

    EditText firestname,lastname,phone,password,email,dob;
    ImageView imgback;
    TextInputLayout dobt;
    String gender,first_name,last_name,mobile,email_str,psswd,dateofbirth,signupID;
    TextView login;
    LinearLayout signup;
    RadioGroup group;
    DatePickerDialog datePickerDialog;
    RadioButton button_male,button_female;
    private ProgressDialog mProgressDialog;

    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_page,container,false);
        ((MainHomeActivity)getActivity()).removeBottom();
        firestname = view.findViewById(R.id.signup_first_name);
        lastname = view.findViewById(R.id.signup_last_name);
        phone = view.findViewById(R.id.signup_phone);
        password= view.findViewById(R.id.signup_password);
        dob= view.findViewById(R.id.signup_dob);
        //dobt = view.findViewById(R.id.textInputLayout18);
        email= view.findViewById(R.id.signup_email);
        imgback = view.findViewById(R.id.img_sign_back);
        group = view.findViewById(R.id.radioGroup2);
        button_male = view.findViewById(R.id.radioButton4);
        button_female = view.findViewById(R.id.radioButton8);
        login=view.findViewById(R.id.signup_login);
        signup= view.findViewById(R.id.signup_lay);


        int gen = group.getCheckedRadioButtonId();
        if(gen==R.id.radioButton4){
            gender="male";
        }else{
            gender="female";
        }


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( firestname.getText().toString().isEmpty()||lastname.getText().toString().isEmpty()||
                        email.getText().toString().trim().isEmpty()|| password.getText().toString().isEmpty()
                        ||phone.getText().toString().isEmpty()|| dob.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Fill in All The Values", Toast.LENGTH_SHORT).show();
                }else{
                    first_name = firestname.getText().toString().trim();
                    last_name = lastname.getText().toString().trim();
                    email_str = email.getText().toString().trim();
                    psswd = password.getText().toString().trim();
                    mobile = phone.getText().toString().trim();
                    dateofbirth=dob.getText().toString().trim();

                    int gen = group.getCheckedRadioButtonId();
                    if(gen==R.id.radioButton4){
                        gender="male";
                    }else{
                        gender="female";
                    }

                    showProgressDialog();
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    retrofit2.Call<SendOTPModel>call = apiInterface.getSendOTPDetail(mobile);
                    call.enqueue(new Callback<SendOTPModel>() {
                        @Override
                        public void onResponse(retrofit2.Call<SendOTPModel> call, Response<SendOTPModel>  response) {

                            // signupID=response.body().getSendOTPDetail().getId();

                            //x = response.body().getSendOTPDetail().getOtp();
                            if (response.body().getSuccess()==200){
                                hideProgressDialog();
                                Toast.makeText(getActivity(),response.body().getMessage() , Toast.LENGTH_SHORT).show();
                                SharedPreferences sp = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor= sp.edit();
                                editor.putString("firstName_signup",first_name);
                                editor.putString("lastname_signup",last_name);
                                editor.putString("gender_signup",gender);
                                editor.putString("dob_signup",dateofbirth);
                                editor.putString("mobile_signup",mobile);
                                editor.putString("email_signup",email_str);
                                editor.putString("password_signup",psswd);
                                editor.apply();


                                removefragment(new EnterOTPFragment());


                            }else if(response.body().getSuccess()==201){
                                hideProgressDialog();

                                Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();

                            }

                        }
                        @Override
                        public void onFailure(retrofit2.Call<SendOTPModel> call, Throwable t) {
                            hideProgressDialog();
                            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });





      /*  if (first_name.isEmpty()) {
            firestname.setError("Please Fill Your Name");
        }
        if(last_name.isEmpty()){
            lastname.setError("Please Fill Your Name");

        }
        if (mobile.isEmpty()) {

            phone.setError("Please Fill Your Phone Number");
        }
        if (psswd.isEmpty()) {

            password.setError("Please Fill Your Password");
        }
        if (email_str.isEmpty()) {

            email.setError("Please Fill Your Email");
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email_str).matches()) {

            email.setError("Should be a Valid Email Address");
        }
        if (mobile.length() != 10 && mobile.length() != 0) {

            phone.setError("please Enter 10 Digits");
        }
        for (int i = 0; i < mobile.length(); i++) {
            char c = mobile.charAt(i);
            if (!(c >= '0' && c <= '9')) {

                phone.setError("Please enter a valid Number");
            }
        }
        if (psswd.length() < 6 && psswd.length() != 0) {

            password.setError("Password Can not be less than 6 letters");
        }
        for (int i = 0; i < first_name.length(); i++) {
            char c = first_name.charAt(i);

            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == ' '))) {

                firestname.setError("Please enter a valid name");
            }
        }
        for (int i = 0; i < last_name.length(); i++) {
            char c = last_name.charAt(i);

            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == ' '))) {

                lastname.setError("Please enter a valid name");
            }
        }


*/


        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removefragment(new LoginScreen());
            }
        });

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



        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
               removefragment(new LoginScreen());
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
