package com.example.xina.kamine.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xina.kamine.Activities.MainHomeActivity;
import com.example.xina.kamine.MainActivity;
import com.example.xina.kamine.Model.LogoutModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class AccountFragment extends android.support.v4.app.Fragment {
ConstraintLayout orders,adddresses,credits;
TextView user_name,user_email,user_mobile_no,logout;
ImageView edit;
String uid;
SharedPreferences preferences;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ((MainHomeActivity)getActivity()).showBottom();
        View view = inflater.inflate(R.layout.my_account_after_login,container,false);
        credits= view.findViewById(R.id.constraintLayout14);
        adddresses= view.findViewById(R.id.constraintLayout15);
        orders= view.findViewById(R.id.constraintLayout12);
        user_name = view.findViewById(R.id.user_name);
        user_email = view.findViewById(R.id.user_email);
        user_mobile_no = view.findViewById(R.id.user_mobile);
        edit = view.findViewById(R.id.edt_pro);


        preferences = getActivity().getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor eg = preferences.edit();
        user_name.setText(preferences.getString("globalname",null));
        user_email.setText(preferences.getString("globalemail",null));
        user_mobile_no.setText(preferences.getString("globalMobile",null));
        eg.commit();
        eg.apply();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removefragment(new UpdateProfile());
            }
        });

        credits.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                ((MainHomeActivity)getActivity()).removeBottom();
                /*android.app.FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit);
                fragmentTransaction.replace(R.id.frag_container, new CreditPageFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
                removefragment(new CreditPageFragment());
            }
        });


        orders.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                ((MainHomeActivity)getActivity()).removeBottom();
               /* android.app.FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit);
                fragmentTransaction.replace(R.id.frag_container, new MyOrders());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
               removefragment(new MyOrders());
            }
        });

        adddresses.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                ((MainHomeActivity)getActivity()).removeBottom();
                removefragment(new ShipmentMain());

            }
        });

        logout = view.findViewById(R.id.Logout_account);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Sure Want To Logout?");
                builder.setMessage("We Will Miss You");
                builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        preferences = getActivity().getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
                        SharedPreferences.Editor eg = preferences.edit();
                        preferences.getString("globalname",null);
                        preferences.getString("globaldob",null);
                        preferences.getString("globalgender",null);
                        preferences.getString("globalLname",null);
                        preferences.getString("globalMobile",null);
                        preferences.getString("globalemail",null);
                        preferences.getString("globalD",null);
                        preferences.getBoolean("hasloggedIN",false);
                        uid=preferences.getString("globalD",null);

                        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                        Call<LogoutModel> call = apiInterface.getlogout(uid);
                        call.enqueue(new Callback<LogoutModel>() {
                            @Override
                            public void onResponse(Call<LogoutModel> call, Response<LogoutModel> response) {
                                SharedPreferences pref =getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.clear();
                                editor.commit();

                                Toast.makeText(getContext(), "Logout Sucessfully", Toast.LENGTH_SHORT).show();
                                reFragment(new HomeFragment());

                            }

                            @Override
                            public void onFailure(Call<LogoutModel> call, Throwable t) {
                                Toast.makeText(getContext(), "Problem Logging Out", Toast.LENGTH_SHORT).show();
                            }
                        });

                        dialog.cancel();


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
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
    void reFragment(android.support.v4.app.Fragment f){
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
        // getActivity().onBackPressed()
    }
}
