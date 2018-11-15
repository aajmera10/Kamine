package com.example.xina.kamine.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.xina.kamine.Fragments.ProductListDisplayFragment;
import com.example.xina.kamine.Model.HomeDisplayDetail;
import com.example.xina.kamine.Model.SubcategoryMainDetail;
import com.example.xina.kamine.R;

import java.util.List;

public class HomeDisplayCategoryAdapter extends RecyclerView.Adapter<HomeDisplayCategoryAdapter.MyViewHolder> {

    Context context;
    List<HomeDisplayDetail> listcategory;

    public HomeDisplayCategoryAdapter(Context context, List<HomeDisplayDetail> listcategory) {
        this.context = context;
        this.listcategory = listcategory;
    }
    public void setCategoryhomelist(List<HomeDisplayDetail> listcategory) {
        this.listcategory = listcategory;
    }
    @NonNull
    @Override
    public HomeDisplayCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_list_display,parent,false);
        return new HomeDisplayCategoryAdapter.MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull HomeDisplayCategoryAdapter.MyViewHolder holder, int position) {
        holder.catename.setText(listcategory.get(position).getSubCategoryName());
        Glide.with(context).load(listcategory.get(position).getImg()).into(holder.image);
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
            catename = itemView.findViewById(R.id.home_category_name);
            image = itemView.findViewById(R.id.home_category_image);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        HomeDisplayDetail clickedDataItem = listcategory.get(pos);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getSubCategoryid(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getSubCategoryName(), Toast.LENGTH_SHORT).show();

                        FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.frag_container, new ProductListDisplayFragment());
                        transaction.addToBackStack(null);
                        transaction.commit();

                        SharedPreferences sp = context.getSharedPreferences("pref",0);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("idvallist",clickedDataItem.getSubCategoryid());
                        ed.putString("idnamelist",clickedDataItem.getSubCategoryName());
                        ed.apply();
                        ed.commit();
                    }
                }
            });

        }


    }

}
