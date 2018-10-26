package com.example.xina.kamine.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.xina.kamine.Model.HomeSliderMainModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ss.com.bannerslider.Slider;
import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainHomeSliderAdapter extends SliderAdapter {



    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    Call<HomeSliderMainModel> call = apiInterface.getMainHomeSlider();

    public Call<HomeSliderMainModel> getCall() {
        call.enqueue(new Callback<HomeSliderMainModel>() {
            @Override
            public void onResponse(Call<HomeSliderMainModel> call, Response<HomeSliderMainModel> response) {
                response.body().getDetail();
            }

            @Override
            public void onFailure(Call<HomeSliderMainModel> call, Throwable t) {

            }
        });
        return call;
    }

    public int getItemCount() {
        return 10;
    }

    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {

        switch (position) {
            case 0:
                imageSlideViewHolder.bindImageSlide("http://xinatechnologies.com/kamine/img/1.png");
                break;
            case 1:
                imageSlideViewHolder.bindImageSlide("http://xinatechnologies.com/kamine/img/2.png");
                break;
            case 2:
                imageSlideViewHolder.bindImageSlide("http://xinatechnologies.com/kamine/img/3.png");
                break;
            case 3:
                imageSlideViewHolder.bindImageSlide("http://xinatechnologies.com/kamine/img/4.png");
                break;
            case 4:
                imageSlideViewHolder.bindImageSlide("http://xinatechnologies.com/kamine/img/4.png");
                break;
            case 5:
                imageSlideViewHolder.bindImageSlide("http://xinatechnologies.com/kamine/img/5.png");
                break;
            case 6:
                imageSlideViewHolder.bindImageSlide("http://xinatechnologies.com/kamine/img/6.png");
                break;
            case 7:
                imageSlideViewHolder.bindImageSlide("http://xinatechnologies.com/kamine/img/7.png");
                break;
            case 8:
                imageSlideViewHolder.bindImageSlide("http://xinatechnologies.com/kamine/img/8.png");
                break;
            case 9:
                imageSlideViewHolder.bindImageSlide("http://xinatechnologies.com/kamine/img/.9png");
                break;
        }
    }

}
