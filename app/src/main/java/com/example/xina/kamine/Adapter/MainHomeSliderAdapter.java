package com.example.xina.kamine.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;

import com.example.xina.kamine.Model.HomeSliderMainDetail;
import com.example.xina.kamine.Model.HomeSliderMainModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ss.com.bannerslider.Slider;
import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainHomeSliderAdapter extends SliderAdapter {
    private Context mCtx;
    private List<HomeSliderMainDetail> HomeSliderMainModel;

    public MainHomeSliderAdapter(Context mCtx, List<HomeSliderMainDetail> homeSliderMainModel) {
        this.mCtx = mCtx;
        this.HomeSliderMainModel = homeSliderMainModel;
    }




    public int getItemCount() {
        return HomeSliderMainModel.size();
    }

    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {

        HomeSliderMainDetail hero = HomeSliderMainModel.get(position);
        //imageSlideViewHolder.bindImageSlide(hero.getId());
        imageSlideViewHolder.bindImageSlide(hero.getImage());

    }

}
