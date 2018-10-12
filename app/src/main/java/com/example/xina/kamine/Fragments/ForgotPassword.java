package com.example.xina.kamine.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.xina.kamine.Model.ForgotPasswordModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends android.support.v4.app.Fragment {
    RadioButton forgotpass_radio;
    EditText forgot_email;
    String uemi;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forgot_password_email,container,false);
        forgotpass_radio = view.findViewById(R.id.forgotpass_radio);
        forgot_email = view.findViewById(R.id.forgot_email);
        uemi = forgot_email.getText().toString().trim();



        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Poppins-Regular.otf");
        forgotpass_radio.setTypeface(font);

        forgotpass_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uemi = forgot_email.getText().toString().trim();
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setTitle("Loading...");
                progressDialog.show();

                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<ForgotPasswordModel> call = apiInterface.getForgotModel(uemi);
                call.enqueue(new Callback<ForgotPasswordModel>() {
                    @Override
                    public void onResponse(Call<ForgotPasswordModel> call, Response<ForgotPasswordModel> response) {
                        if(response.body().getSuccess()==200){
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            removefragment(new LoginScreen());
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ForgotPasswordModel> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
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
