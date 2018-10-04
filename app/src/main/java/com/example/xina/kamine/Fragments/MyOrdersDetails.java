package com.example.xina.kamine.Fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xina.kamine.Adapter.ProductCardViewAdapter;
import com.example.xina.kamine.Model.ProductCardModel;
import com.example.xina.kamine.R;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersDetails extends android.support.v4.app.Fragment {

    RecyclerView recview_orderdetails;
    List<ProductCardModel> product_detail_list;
    ConstraintLayout retndxchange;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_detail_page,container,false);

        recview_orderdetails = view.findViewById(R.id.recview_orderdetails);
        recview_orderdetails.setLayoutManager(new LinearLayoutManager(getActivity()));
        recview_orderdetails.setHasFixedSize(true);
        recview_orderdetails.setNestedScrollingEnabled(false);


        product_detail_list = new ArrayList<>();

        product_detail_list.add(new ProductCardModel(R.drawable.image2,"New Design","Rs.500","Rs.800","30%",
                "Multicolor","L","1","Delivered"));
        product_detail_list.add(new ProductCardModel(R.drawable.image2,"New Design","Rs.500","Rs.800","30%",
                "Multicolor","L","1","Delivered"));
        product_detail_list.add(new ProductCardModel(R.drawable.image2,"New Design","Rs.500","Rs.800","30%",
                "Multicolor","L","1","Delivered"));
        product_detail_list.add(new ProductCardModel(R.drawable.image2,"New Design","Rs.500","Rs.800","30%",
                "Multicolor","L","1","Delivered"));

        ProductCardViewAdapter cardada = new ProductCardViewAdapter(getActivity(),product_detail_list);
        recview_orderdetails.setAdapter(cardada);




retndxchange = view.findViewById(R.id.retndxchange);
retndxchange.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        replaceFragment(new ExchaneAndReturn());
    }
});
        return view;
    }

    @SuppressLint("ResourceType")
    private void replaceFragment(android.support.v4.app.Fragment f) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction  = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit);
        //bottomNavigationView.setVisibility(View.VISIBLE);
        transaction.replace(R.id.frag_container,f);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
