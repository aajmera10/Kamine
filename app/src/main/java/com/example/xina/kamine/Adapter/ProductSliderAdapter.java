package com.example.xina.kamine.Adapter;

import android.content.Context;

import com.example.xina.kamine.Model.HomeSliderMainDetail;
import com.example.xina.kamine.Model.ProductDisplayColorItem;
import com.example.xina.kamine.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

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
        ProductDisplayColorItem item = ProductDisplayColorItem.get(position);
         String image=item.getImg();
         String[] elements = image.split(",");
         List<String> fixedLenghtList = Arrays.asList(elements);
         String i1 = elements[0];
         String i2 = elements[1];
         String i3 = elements[2];
         String i4 = elements[3];
         String i5 = elements[4];
         ArrayList<String> listOfString = new ArrayList<String>(fixedLenghtList);
        StringTokenizer tokenizer = new StringTokenizer(item.getImg(), ",");
       /* while (tokenizer.hasMoreTokens()) {
            System.out.println(tokenizer.nextToken());
        }*/
        //viewHolder.bindImageSlide(tokenizer.nextToken());
        switch (position) {
            case 0:
                viewHolder.bindImageSlide(i1);
                //R.drawable.image;
                //viewHolder.bindImageSlide("https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg");
                break;
            case 1:
                //viewHolder.bindImageSlide("https://assets.materialup.com/uploads/20ded50d-cc85-4e72-9ce3-452671cf7a6d/preview.jpg");
                viewHolder.bindImageSlide(i2);
                break;
            case 2:
                viewHolder.bindImageSlide("https://assets.materialup.com/uploads/76d63bbc-54a1-450a-a462-d90056be881b/preview.png");
               // viewHolder.bindImageSlide(i3);
                break;
            case 3:
                viewHolder.bindImageSlide("https://assets.materialup.com/uploads/76d63bbc-54a1-450a-a462-d90056be881b/preview.png");
                //viewHolder.bindImageSlide(i4);
                break;
            case 4 :
                viewHolder.bindImageSlide("https://assets.materialup.com/uploads/76d63bbc-54a1-450a-a462-d90056be881b/preview.png");
                //viewHolder.bindImageSlide(i5);
                }

    }

}
