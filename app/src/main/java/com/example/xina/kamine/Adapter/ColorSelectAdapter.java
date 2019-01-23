package com.example.xina.kamine.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.xina.kamine.Fragments.ProductListDisplayFragment;
import com.example.xina.kamine.Fragments.ProductsPageDisplayFragment;
import com.example.xina.kamine.Model.ProductDisplayColorItem;
import com.example.xina.kamine.Model.SubcategoryMainDetail;
import com.example.xina.kamine.R;

import java.util.List;

public class ColorSelectAdapter extends RecyclerView.Adapter<ColorSelectAdapter.MyViewHolder> {
    Context con;
    List<ProductDisplayColorItem> productDisplayColorItems;


    public ColorSelectAdapter(Context con, List<ProductDisplayColorItem> productDisplayColorItems) {
        this.con = con;
        this.productDisplayColorItems = productDisplayColorItems;
    }



    public void setProductDisplayColorItems(List<ProductDisplayColorItem> productDisplayColorItems) {
        this.productDisplayColorItems = productDisplayColorItems;

    }

    @NonNull
    @Override
    public ColorSelectAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(con).inflate(R.layout.circle_of_color,parent,false);
        return new ColorSelectAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorSelectAdapter.MyViewHolder holder, int position) {

        Glide.with(con).load(productDisplayColorItems.get(position).getColorImgUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productDisplayColorItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cire_death);
            final int pos = getAdapterPosition();
            SharedPreferences sp = con.getSharedPreferences("pref",0);
            SharedPreferences.Editor ed = sp.edit();
            ed.putInt("colorpos",pos);
            ed.putBoolean("ddd0",true);
            ed.apply();

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    //ProductDisplayColorItem clickdataitem = productDisplayColorItems.get(pos);
                    if(getAdapterPosition()!=-1) {
                        // Do your stuff here
                        //ProductDisplayColorItem clickdataitem = productDisplayColorItems.get(pos);
                        FragmentManager manager = ((AppCompatActivity)con).getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.frag_container, new ProductsPageDisplayFragment());
                        transaction.addToBackStack(null);
                        transaction.commit();

                        Toast.makeText(con, String.valueOf(pos), Toast.LENGTH_SHORT).show();
                        SharedPreferences sp = con.getSharedPreferences("pref",0);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putInt("colorpos",pos);
                        ed.apply();}
                }
            });
        }
    }
}
