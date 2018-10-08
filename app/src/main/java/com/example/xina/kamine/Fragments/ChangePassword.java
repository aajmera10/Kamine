package com.example.xina.kamine.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xina.kamine.Model.ChangePswdModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;
import com.example.xina.kamine.Utils.PoppinsEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends Fragment {

    EditText e_new_password,e_old_password,e_confirm_new_password;
    ConstraintLayout change_psswd_layout;
    String new_password,old_password,confirm_new_password,useridStr;
    SharedPreferences sp;
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

        change_psswd_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                old_password = e_old_password.getText().toString().trim();
                new_password = e_new_password.getText().toString().trim();
                confirm_new_password = e_confirm_new_password.getText().toString().trim();

                if (TextUtils.isEmpty(old_password)) {
                    e_old_password.setError(getString(R.string.empty_old_pswd));
                    e_old_password.requestFocus();
                } else if (TextUtils.isEmpty(new_password)) {
                    e_new_password.setError(getString(R.string.empty_new_pswd));
                    e_new_password.requestFocus();
                } else if (TextUtils.isEmpty(confirm_new_password)) {
                    e_confirm_new_password.setError(getString(R.string.empty_confirm_pswd));
                    e_confirm_new_password.requestFocus();
                } else if (!TextUtils.equals(confirm_new_password, new_password)) {
                    e_confirm_new_password.setError(getString(R.string.pswd_not_match));
                    e_confirm_new_password.requestFocus();
                } else {
                    changePswdApi();
                }
            }
        });

        return view;
    }

    private void changePswdApi() {
        sp=getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        useridStr = sp.getString("globalD","");

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ChangePswdModel> changePswdModelCall = apiInterface.getChangePswd(useridStr,old_password,new_password);
        changePswdModelCall.enqueue(new Callback<ChangePswdModel>() {
            @Override
            public void onResponse(Call<ChangePswdModel> call, Response<ChangePswdModel> response) {
                if (response.body().getSuccess() == 200){
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChangePswdModel> call, Throwable t) {

            }
        });

    }
}
