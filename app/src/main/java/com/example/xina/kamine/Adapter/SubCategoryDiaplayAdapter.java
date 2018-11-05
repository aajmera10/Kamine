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

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();

                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){
                        SubcategoryMainDetail clickedDataItem = listcategory.get(pos);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getId(), Toast.LENGTH_SHORT).show();

                        FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.frag_container, new ProductListDisplayFragment());
                        transaction.addToBackStack(null);
                        transaction.commit();

                       // String c = clickedDataItem.get
                        SharedPreferences sp = context.getSharedPreferences("pref",0);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("idvallist",clickedDataItem.getId());
                        ed.putString("idnamelist",clickedDataItem.getName());
                        ed.apply();
                        ed.commit();
                    }
                }
            });

        }


    }

}
