package com.example.xina.kamine.Fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xina.kamine.Model.SendOTPModel;
import com.example.xina.kamine.Model.UpdateProfileDetail;
import com.example.xina.kamine.Model.UpdateProfileOTPModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;
import com.goodiebag.pinview.Pinview;

import org.apache.http.ConnectionReuseStrategy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileOTP extends Fragment {

    Pinview pview;
    TextView mobilechange;
    ConstraintLayout otpupdatev;
    private ProgressDialog mProgressDialog;
    SharedPreferences sharedPreferences;
    String gender, first_name, last_name, email_str, psswd, dateofbirth, signupID, phone, otp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_profile_otp, container, false);

        pview = view.findViewById(R.id.pinview2);
        mobilechange = view.findViewById(R.id.up_otp_no);
        otpupdatev = view.findViewById(R.id.sub_otp_update);
        sharedPreferences = getActivity().getSharedPreferences("pref",0);
        mobilechange.setText(sharedPreferences.getString("globalMobile", ""));
        otpupdatev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showProgressDialog();

                otp = pview.getValue().toString().trim();
                sharedPreferences = getActivity().getSharedPreferences("pref",0);
                first_name = sharedPreferences.getString("globalname", "");
                last_name = sharedPreferences.getString("globalLname", "");
                dateofbirth = sharedPreferences.getString("globaldob", "");
                email_str = sharedPreferences.getString("globalemail", "");
                phone = sharedPreferences.getString("globalMobile", "");
                gender = sharedPreferences.getString("globalgender", "");
                signupID = sharedPreferences.getString("globalD", "");


                if (pview.getValue().isEmpty()) {
                    hideProgressDialog();
                    Toast.makeText(getContext(), "Please Pill in the correct OTP", Toast.LENGTH_SHORT).show();
                } else {

                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                   Call<UpdateProfileOTPModel> call = apiInterface.getUpdateProfieOTP(signupID,first_name,last_name,gender,email_str,dateofbirth,
                           phone,otp);
                   call.enqueue(new Callback<UpdateProfileOTPModel>() {
                       @Override
                       public void onResponse(Call<UpdateProfileOTPModel> call, Response<UpdateProfileOTPModel> response) {
                           if(response.body().getSuccess() == 200){
                               hideProgressDialog();
                               Toast.makeText(getContext(), "Credentials Sucessfully Updated", Toast.LENGTH_SHORT).show();
                               removefragment(new UpdateProfile());
                           }
                       }

                       @Override
                       public void onFailure(Call<UpdateProfileOTPModel> call, Throwable t) {
                            hideProgressDialog();
                           Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   });




                }
            }
        });


        return view;

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
        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.commit();
    }
}
