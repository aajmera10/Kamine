package com.example.xina.kamine.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xina.kamine.R;
import com.goodiebag.pinview.Pinview;

import org.apache.http.ConnectionReuseStrategy;

public class UpdateProfileOTP extends Fragment {

    Pinview pview;
    TextView mobilechange;
    ConstraintLayout otpupdatev;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_profile_otp,container,false);

        pview= view.findViewById(R.id.pinview2);
        mobilechange = view.findViewById(R.id.up_otp_no);
        otpupdatev = view.findViewById(R.id.sub_otp_update);


otpupdatev.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(pview.getValue().isEmpty()){


        }else{


        }
    }
});







        return view;

    }
}
