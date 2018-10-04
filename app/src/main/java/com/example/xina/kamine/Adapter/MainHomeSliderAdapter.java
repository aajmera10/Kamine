package com.example.xina.kamine.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.xina.kamine.R;

import ss.com.bannerslider.Slider;
import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainHomeSliderAdapter extends SliderAdapter {

    public int getItemCount() {
        return 10;
    }

    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {

        switch (position) {
            case 0:
                imageSlideViewHolder.bindImageSlide(R.drawable.image);
                break;
            case 1:
                imageSlideViewHolder.bindImageSlide(R.drawable.image2);
                break;
            case 2:
                imageSlideViewHolder.bindImageSlide(R.drawable.image3);
                break;
            case 3:
                imageSlideViewHolder.bindImageSlide(R.drawable.im1);
                break;
            case 4:
                imageSlideViewHolder.bindImageSlide(R.drawable.im2);
                break;
            case 5:
                imageSlideViewHolder.bindImageSlide(R.drawable.im3);
                break;
            case 6:
                imageSlideViewHolder.bindImageSlide(R.drawable.im4);
                break;
            case 7:
                imageSlideViewHolder.bindImageSlide(R.drawable.im5);
                break;
            case 8:
                imageSlideViewHolder.bindImageSlide(R.drawable.im6);
                break;
            case 9:
                imageSlideViewHolder.bindImageSlide(R.drawable.im7);
                break;
        }
    }

}
