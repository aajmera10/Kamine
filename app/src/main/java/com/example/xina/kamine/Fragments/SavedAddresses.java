package com.example.xina.kamine.Fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.xina.kamine.Adapter.SelectAddressCardAdapter;
import com.example.xina.kamine.Model.SelectAddressCardModel;
import com.example.xina.kamine.R;

import java.util.ArrayList;
import java.util.List;

public class SavedAddresses extends android.support.v4.app.Fragment {

    ConstraintLayout layout_order_summary,layout_ontouch,layout_newAddress,proced_to_payment;
    RecyclerView saved_addresses;
    ImageView backbtn;
    List<SelectAddressCardModel> listsaves;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shipment_saved_address,container,false);

        layout_order_summary = view.findViewById(R.id.constraintLayout20);
        layout_ontouch= view.findViewById(R.id.constraintLayout21);
       // layout_newAddress= view.findViewById(R.id.new_add);
        proced_to_payment= view.findViewById(R.id.constraintLayout19);

        //backbtn = view.findViewById(R.id.imageView20);

        layout_ontouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout_order_summary.isShown()){
                    layout_order_summary.setVisibility(View.GONE);
                }else{
                    layout_order_summary.setVisibility(View.VISIBLE);
                }

            }
        });

        saved_addresses= view.findViewById(R.id.rec_view_savedaddress);
        saved_addresses.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        saved_addresses.setHasFixedSize(true);
        saved_addresses.setNestedScrollingEnabled(false);
        listsaves = new ArrayList<>();
        listsaves.add(new SelectAddressCardModel("Imean khan","125,D-Block, Near X School","Malviya Nagar, Jaipur",
                "Rajasthan,India","302020","854782652263"));
        listsaves.add(new SelectAddressCardModel("Imean khan","125,D-Block, Near X School","Malviya Nagar, Jaipur",
                "Rajasthan,India","302020","854782652263"));
        listsaves.add(new SelectAddressCardModel("Imean khan","125,D-Block, Near X School","Malviya Nagar, Jaipur",
                "Rajasthan,India","302020","854782652263"));

        SelectAddressCardAdapter listmain = new SelectAddressCardAdapter(getActivity(),listsaves);
        saved_addresses.setAdapter(listmain);

        //getActivity().onBackPressed();


      /*  layout_newAddress.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                android.app.FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit);
                fragmentTransaction.replace(R.id.frag_container, new AddNewAddress());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });*/





        return view;
    }
}
