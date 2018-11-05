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
import android.widget.TextView;
import android.widget.Toast;

import com.example.xina.kamine.Fragments.DayOtherCategoryFragment;
import com.example.xina.kamine.Fragments.ProductListDisplayFragment;
import com.example.xina.kamine.Model.OtherCategoryDetail;
import com.example.xina.kamine.Model.SubcategoryMainDetail;
import com.example.xina.kamine.R;

import java.util.List;

public class CollectionPageAdapter extends RecyclerView.Adapter<CollectionPageAdapter.MyViewHolder> {

    Context context;
    List<OtherCategoryDetail> list;

    public void setListcoll(List<OtherCategoryDetail> list){
        this.list = list;

    }

    public CollectionPageAdapter(Context context, List<OtherCategoryDetail> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public CollectionPageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.collection_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionPageAdapter.MyViewHolder holder, int position) {

        holder.tv.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.daytext);
            //int pos = getAdapterPosition();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        OtherCategoryDetail clickedDataItem = list.get(pos);
                        FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.frag_container, new DayOtherCategoryFragment());
                        transaction.addToBackStack(null);
                        transaction.commit();

                        Toast.makeText(v.getContext(), clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(v.getContext(), clickedDataItem.getId(), Toast.LENGTH_SHORT).show();


                        SharedPreferences sp = context.getSharedPreferences("pref",0);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("idcollectionlist",clickedDataItem.getId());
                        ed.putString("idday",clickedDataItem.getName());
                        ed.commit();
                        ed.apply();
                }
            }
            });
        }
    }}

