package com.example.xina.kamine.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.xina.kamine.Adapter.ProductSliderAdapter;
import com.example.xina.kamine.Adapter.PicassoImageLoadingService;
import com.example.xina.kamine.Adapter.ProductsPageAdapter;
import com.example.xina.kamine.Model.ProductsPageModel;
import com.example.xina.kamine.R;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class ProductsPageDisplayFragment extends Fragment {
    Slider slider;
    RecyclerView rec_seemore;
    List<ProductsPageModel> show_product;
    TextView show,hide;
    ConstraintLayout showlay;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_display_page,container,false);

        Slider.init(new PicassoImageLoadingService(getActivity()));
        Toolbar toolbar = view.findViewById(R.id.toolbar_display_page);
        AppCompatActivity activity = (AppCompatActivity)getActivity();

        //activity.getSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(activity.getSupportActionBar() != null)
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        slider = view.findViewById(R.id.banner_slider1);
        slider.setAdapter(new ProductSliderAdapter());

        rec_seemore = (RecyclerView) view.findViewById(R.id.see_more);
        rec_seemore.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        // ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
        //int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_offset);
        //recyclerView1.addItemDecoration(new ClosetFragment.GridSpacingItemDecoration(1, spacingInPixels, true, 0));
        // recyclerView1.addItemDecoration(itemDecoration);
        rec_seemore.setHasFixedSize(true);
        rec_seemore.setNestedScrollingEnabled(false);

        show_product = new ArrayList<>();

        show_product.add(new ProductsPageModel("Kamine", "Rs.590", "Rs.750", "25% off", "the best design at the price", R.drawable.image));
        show_product.add(new ProductsPageModel("Kamine", "Rs.590", "RS.750", "25% off", "the best design at the price", R.drawable.image2));
        show_product.add(new ProductsPageModel("Kamine", "Rs.590", "750", "25%", "the best design at the price", R.drawable.image3));
        show_product.add(new ProductsPageModel("Kamine", "Rs.590", "750", "25%", "the best design at the price", R.drawable.image));
        show_product.add(new ProductsPageModel("Kamine", "Rs.590", "750", "25%", "the best design at the price", R.drawable.image2));
        show_product.add(new ProductsPageModel("Kamine", "Rs.590", "750", "25%", "the best design at the price", R.drawable.image3));
        show_product.add(new ProductsPageModel("Kamine", "Rs.590", "750", "25%", "the best design at the price", R.drawable.image));
        show_product.add(new ProductsPageModel("Kamine", "Rs.590", "750", "25%", "the best design at the price", R.drawable.image2));
        show_product.add(new ProductsPageModel("Kamine", "Rs.590", "750", "25%", "the best design at the price", R.drawable.image3));
        show_product.add(new ProductsPageModel("Kamine", "Rs.590", "750", "25%", "the best design at the price", R.drawable.image));


        ProductsPageAdapter ppadapt = new ProductsPageAdapter(getActivity(), show_product);
        rec_seemore.setAdapter(ppadapt);

        show = view.findViewById(R.id.textView82);
        showlay = view.findViewById(R.id.constraintLayout18);
        hide = view.findViewById(R.id.textView162);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.setVisibility(View.GONE);
                showlay.setVisibility(View.VISIBLE);
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.setVisibility(View.VISIBLE);
                showlay.setVisibility(View.GONE);

            }
        });



        return view;
    }
}
