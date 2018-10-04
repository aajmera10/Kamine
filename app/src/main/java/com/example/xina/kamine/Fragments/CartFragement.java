package com.example.xina.kamine.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xina.kamine.Adapter.ProductCardViewAdapter;
import com.example.xina.kamine.Adapter.ProductCartAdapter;
import com.example.xina.kamine.Model.CartProductModel;
import com.example.xina.kamine.R;

import java.util.ArrayList;
import java.util.List;

public class CartFragement extends Fragment {
RecyclerView cart_recview;
List<CartProductModel> cartlist;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_layout_main,container,false);

        cart_recview = view.findViewById(R.id.cart_recview);
        cart_recview.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        cart_recview.setHasFixedSize(true);
        cart_recview.setNestedScrollingEnabled(false);

        cartlist = new ArrayList<>();
        cartlist.add(new CartProductModel(R.drawable.image2,"Xdesign","Rs1800","3000",
                "40%","MultiColor","XXl","3"));
        cartlist.add(new CartProductModel(R.drawable.image,"Xdesign","Rs1800","3000",
                "40%","MultiColor","XXl","3"));
        cartlist.add(new CartProductModel(R.drawable.image3,"Xdesign","Rs1800","3000",
                "40%","MultiColor","XXl","3"));
        cartlist.add(new CartProductModel(R.drawable.image2,"Xdesign","Rs1800","3000",
                "40%","MultiColor","XXl","3"));

        ProductCartAdapter cartada = new ProductCartAdapter(getActivity(),cartlist);

        cart_recview.setAdapter(cartada);


        return view;
    }
}
