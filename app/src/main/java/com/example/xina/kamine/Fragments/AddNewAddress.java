package com.example.xina.kamine.Fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xina.kamine.Model.SaveAddressModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewAddress extends android.support.v4.app.Fragment {
    ConstraintLayout save_address,cancel;
    EditText fname,lname,pincode,city,state,country,landmark,phone,add1,add2;
    String firstn,lastn,pin,cit,stat,cou,land,mobile,address,addone,addtwo;
    private ProgressDialog mProgressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_address,container,false);
        fname = view.findViewById(R.id.saveadd_fname);
        lname = view.findViewById(R.id.saveadd_lanme);
        pincode = view.findViewById(R.id.saveadd_pincode);
        city = view.findViewById(R.id.saveadd_city);
        state = view.findViewById(R.id.saveadd_state);
        country = view.findViewById(R.id.saveadd_country);
        landmark = view.findViewById(R.id.saveadd_landmark);
        phone = view.findViewById(R.id.saveadd_phone);
        add1 = view.findViewById(R.id.saveadd_addline1);
        add2 = view.findViewById(R.id.saveadd_addline2);


        save_address = view.findViewById(R.id.constraintLayout26);
        cancel = view.findViewById(R.id.constraintLayout25);

        save_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fname.getText().toString().isEmpty()||lname.getText().toString().isEmpty()||
                        pincode.getText().toString().isEmpty() ||city.getText().toString().isEmpty()
                        ||state.getText().toString().isEmpty()||country.getText().toString().isEmpty()||
                        phone.getText().toString().isEmpty()||add1.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Please Fill in All the fields correctly", Toast.LENGTH_SHORT).show();
                }else{
                    firstn = fname.getText().toString().trim();
                    lastn = lname.getText().toString().trim();
                    pin = pincode.getText().toString().trim();
                    //cit,stat,cou,land,mobile,address
                    cit = city.getText().toString().trim();
                    stat = state.getText().toString().trim();
                    cou = country.getText().toString().trim();
                    land = landmark.getText().toString().trim();
                    mobile = phone.getText().toString().trim();
                    addone = add1.getText().toString().trim();
                    addtwo = add2.getText().toString().trim();
                    address = addone.concat(addtwo);

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pref",0);
                   String id =  sharedPreferences.getString("globalID","");

                    showProgressDialog();
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<SaveAddressModel>call = apiInterface.getSavedAddress(id,firstn,lastn,address,land,mobile,pin,cit,stat,cou);
                    call.enqueue(new Callback<SaveAddressModel>() {
                        @Override
                        public void onResponse(Call<SaveAddressModel> call, Response<SaveAddressModel> response) {
                            hideProgressDialog();
                            if(response.body().getSuccess()==200){
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<SaveAddressModel> call, Throwable t) {
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

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
