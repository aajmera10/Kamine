package com.example.xina.kamine.Fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
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
import android.widget.TextView;

import com.example.xina.kamine.Activities.MainHomeActivity;
import com.example.xina.kamine.R;

import static android.content.Context.MODE_PRIVATE;

public class AccountFragment extends android.support.v4.app.Fragment {
ConstraintLayout orders,adddresses,credits;
TextView user_name,user_email,user_mobile_no;
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


        preferences = getActivity().getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor eg = preferences.edit();
        user_name.setText(preferences.getString("globalname",""));
        //user_email.setText(preferences.getString("",""));
        user_mobile_no.setText(preferences.getString("globalMobile",""));
        eg.commit();
        eg.apply();

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
}
