package com.example.xina.kamine.Adapter;

import android.content.Context;

import com.example.xina.kamine.Model.HomeSliderMainDetail;
import com.example.xina.kamine.Model.ProductDisplayColorItem;
import com.example.xina.kamine.R;

import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class ProductSliderAdapter extends SliderAdapter {

    private Context mCtx;
    private List<ProductDisplayColorItem> ProductDisplayColorItem;

    public ProductSliderAdapter(Context mCtx, List<ProductDisplayColorItem> ProductDisplayColorItem) {
        this.mCtx = mCtx;
        this.ProductDisplayColorItem = ProductDisplayColorItem;
    }
    @Override
    public int getItemCount() {
        return ProductDisplayColorItem.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:

                viewHolder.bindImageSlide(R.drawable.image);
                //R.drawable.image;
                //viewHolder.bindImageSlide("https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg");
                break;
            case 1:
                //viewHolder.bindImageSlide("https://assets.materialup.com/uploads/20ded50d-cc85-4e72-9ce3-452671cf7a6d/preview.jpg");
                viewHolder.bindImageSlide(R.drawable.image3);

                break;
            case 2:
               // viewHolder.bindImageSlide("https://assets.materialup.com/uploads/76d63bbc-54a1-450a-a462-d90056be881b/preview.png");
                viewHolder.bindImageSlide(R.drawable.img1);

                break;
            case 3:
                viewHolder.bindImageSlide(R.drawable.img2);
                break;

                }
                ProductDisplayColorItem item = ProductDisplayColorItem.get(position);
                        viewHolder.bindImageSlide(item.getColorImgUrl());
    }

}
