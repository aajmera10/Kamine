package com.example.xina.kamine.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.PoppinsEditText;

public class ChangePassword extends Fragment {

    EditText e_new_password,e_old_password,e_confirm_new_password;
    ConstraintLayout change_psswd_layout;
    String new_password,old_password,confirm_new_password;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_password_screen,container,false);

        e_old_password = view.findViewById(R.id.c_pwd_old);
        e_new_password = view.findViewById(R.id.c_pwd_new);
        e_confirm_new_password = view.findViewById(R.id.c_pwd_new_con);
        change_psswd_layout = view.findViewById(R.id.change_psswd_layout);

        old_password = e_old_password.getText().toString().trim();
        new_password = e_new_password.getText().toString().trim();
        confirm_new_password = e_confirm_new_password.getText().toString().trim();

        return view;
    }
}
