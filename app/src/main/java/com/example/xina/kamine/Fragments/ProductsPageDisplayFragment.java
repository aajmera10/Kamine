package com.example.xina.kamine.Fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.xina.kamine.Adapter.ColorSelectAdapter;
import com.example.xina.kamine.Adapter.ProductSliderAdapter;
import com.example.xina.kamine.Adapter.PicassoImageLoadingService;
import com.example.xina.kamine.Adapter.ProductsPageAdapter;
import com.example.xina.kamine.Adapter.SubCategoryDiaplayAdapter;
import com.example.xina.kamine.Model.AddToWishlistModel;
import com.example.xina.kamine.Model.HomeSliderMainDetail;
import com.example.xina.kamine.Model.ProductDisplayColorItem;
import com.example.xina.kamine.Model.ProductDisplayModel;
import com.example.xina.kamine.Model.ProductsPageModel;
import com.example.xina.kamine.Model.SendOTPModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ss.com.bannerslider.Slider;

public class ProductsPageDisplayFragment extends Fragment {
    Slider slider;
    ImageView wishlistW;
    RecyclerView rec_seemore,color_recv;
    List<ProductsPageModel> show_product;
    List<ProductDisplayColorItem> productDisplayColorItems;
    ColorSelectAdapter colorSelectAdapter;
    TextView show,hide,mrp,sellprice,discount,productname,productdescription;
    ConstraintLayout showlay;
    String x,id;
    SharedPreferences sp;
    private ProgressDialog mProgressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_display_page,container,false);
        showProgressDialog();
        Slider.init(new PicassoImageLoadingService(getActivity()));
        Toolbar toolbar = view.findViewById(R.id.toolbar_display_page);
        AppCompatActivity activity = (AppCompatActivity)getActivity();

        ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(activity.getSupportActionBar() != null)

            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        slider = view.findViewById(R.id.banner_slider1);
        wishlistW = view.findViewById(R.id.wishlist);
        //slider.setAdapter(new ProductSliderAdapter());
        mrp= view.findViewById(R.id.productcost);
        sellprice = view.findViewById(R.id.productsellprice);
        discount = view.findViewById(R.id.properoff);
        rec_seemore = view.findViewById(R.id.see_more);
        productdescription = view.findViewById(R.id.productdescrip);
        productname = view.findViewById(R.id.productname);
        color_recv = view.findViewById(R.id.color_recv);

        rec_seemore.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        rec_seemore.setHasFixedSize(true);
        rec_seemore.setNestedScrollingEnabled(false);

        show_product = new ArrayList<>();

        show_product.add(new ProductsPageModel("Kamine", "590", "750", "25", "the best design at the price", R.drawable.image));
        show_product.add(new ProductsPageModel("Kamine", "590", "750", "25", "the best design at the price", R.drawable.image2));
        show_product.add(new ProductsPageModel("Kamine", "590", "750", "25", "the best design at the price", R.drawable.image3));
        show_product.add(new ProductsPageModel("Kamine", "590", "750", "25", "the best design at the price", R.drawable.image));
        show_product.add(new ProductsPageModel("Kamine", "590", "750", "25", "the best design at the price", R.drawable.image2));
        show_product.add(new ProductsPageModel("Kamine", "590", "750", "25", "the best design at the price", R.drawable.image3));
        show_product.add(new ProductsPageModel("Kamine", "590", "750", "25", "the best design at the price", R.drawable.image));
        show_product.add(new ProductsPageModel("Kamine", "590", "750", "25", "the best design at the price", R.drawable.image2));
        show_product.add(new ProductsPageModel("Kamine", "590", "750", "25", "the best design at the price", R.drawable.image3));



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

        sp = getActivity().getSharedPreferences("pref",0);
        x = sp.getString("idvalproduct","");
        id = sp.getString("globalD","");

        //color_recv.setLayoutManager(new GridLayoutManager(getActivity(),GridLayoutManager.HORIZONTAL,1, false));
        color_recv.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL, false));
        color_recv.setHasFixedSize(true);
        color_recv.setNestedScrollingEnabled(false);
        colorSelectAdapter = new ColorSelectAdapter(getContext(),productDisplayColorItems);


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductDisplayModel> call = apiInterface.getprodyctdisplaypage(x);
        call.enqueue(new Callback<ProductDisplayModel>() {
            @Override
            public void onResponse(Call<ProductDisplayModel> call, Response<ProductDisplayModel> response) {
                sp = getActivity().getSharedPreferences("pref",0);
                int c =    sp.getInt("colorpos",0);

                    productname.setText(response.body().getProductDisplayDetail().getProductName());
                    discount.setText(response.body().getProductDisplayDetail().getDiscount());
                    mrp.setText(response.body().getProductDisplayDetail().getMrp());
                    productdescription.setText(response.body().getProductDisplayDetail().getDescription());
                    sellprice.setText(response.body().getProductDisplayDetail().getPrice());

                productDisplayColorItems = response.body().getProductDisplayDetail().getColor();
                colorSelectAdapter.setProductDisplayColorItems(productDisplayColorItems);
                color_recv.setAdapter(colorSelectAdapter);
                List<ProductDisplayColorItem> ProductDisplayColorItem = response.body().getProductDisplayDetail().getColor();
                Slider.init(new PicassoImageLoadingService(getActivity()));
                slider.setAdapter(new ProductSliderAdapter(getContext(),ProductDisplayColorItem));
                //}  hideProgressDialog();
                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<ProductDisplayModel> call, Throwable t) {
                hideProgressDialog();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

            wishlistW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    showProgressDialog();
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<AddToWishlistModel>addToWishlistModelCall =apiInterface.getaddtowishlist(id,x);
                    addToWishlistModelCall.enqueue(new Callback<AddToWishlistModel>() {
                        @Override
                        public void onResponse(Call<AddToWishlistModel> call, Response<AddToWishlistModel> response) {
                            if(response.body().getSuccess()==200){
                                response.body().getAddToWishlistDetail().getId();
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                hideProgressDialog();
                            }else if (response.body().getSuccess()==202){
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                hideProgressDialog();
                            }
                        }
                        @Override
                        public void onFailure(Call<AddToWishlistModel> call, Throwable t) {
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

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
