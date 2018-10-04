package com.example.xina.kamine.Fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.xina.kamine.R;

public class ForgotPassword extends android.support.v4.app.Fragment {
    RadioButton forgotpass_radio;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forgot_password_email,container,false);
        forgotpass_radio = view.findViewById(R.id.forgotpass_radio);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Poppins-Regular.otf");
        forgotpass_radio.setTypeface(font);

        return view;
    }
}
