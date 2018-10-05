package com.example.xina.kamine.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xina.kamine.R;
import com.mukesh.OtpView;

public class EnterOTPFragment extends Fragment {
    ConstraintLayout submit;
    TextView usr_no;
    private OtpView otpView;

    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.submit_otp,container,false);
        sp=getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);












        return view;
    }
}
