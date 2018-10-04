package com.example.xina.kamine.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xina.kamine.R;


public class CreditPageFragment extends Fragment {
ConstraintLayout Ordersummary,ontouchshow;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_page_main,container,false);
        Ordersummary = view.findViewById(R.id.constraintLayout4);
        ontouchshow = view.findViewById(R.id.constraintLayout5);


        Ordersummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ontouchshow.isShown()){
                    ontouchshow.setVisibility(View.GONE);
                }else{
                    ontouchshow.setVisibility(View.VISIBLE);
                }

            }
        });



        return view;
    }
}
