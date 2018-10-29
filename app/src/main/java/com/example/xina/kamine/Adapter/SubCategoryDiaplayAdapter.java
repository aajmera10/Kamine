package com.example.xina.kamine.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xina.kamine.Model.SubcategoryMainDetail;
import com.example.xina.kamine.R;

import java.util.List;

public class SubCategoryDiaplayAdapter extends RecyclerView.Adapter<SubCategoryDiaplayAdapter.MyViewHolder>{
    Context context;
    List<SubcategoryMainDetail> listcategory;

    public void setCategorylist(List<SubcategoryMainDetail> listcategory) {
        this.listcategory = listcategory;
        //notifyDataSetChanged();
    }

    public SubCategoryDiaplayAdapter(Context context, List<SubcategoryMainDetail> listcategory) {
        this.context = context;
        this.listcategory = listcategory;
    }


    @NonNull
    @Override
    public SubCategoryDiaplayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_display_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryDiaplayAdapter.MyViewHolder holder, int position) {

        holder.catename.setText(listcategory.get(position).getName());
        Glide.with(context).load(listcategory.get(position).getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
            return listcategory.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView catename;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            catename = itemView.findViewById(R.id.category_name);
            image = itemView.findViewById(R.id.imageView1123);

        }
    }

}
