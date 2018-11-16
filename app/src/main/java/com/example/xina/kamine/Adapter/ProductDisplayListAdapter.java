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
import com.example.xina.kamine.Fragments.ProductsPageDisplayFragment;
import com.example.xina.kamine.Model.ProductListDetailI;
import com.example.xina.kamine.Model.SubcategoryMainDetail;
import com.example.xina.kamine.R;

import java.util.List;

public class ProductDisplayListAdapter extends RecyclerView.Adapter<ProductDisplayListAdapter.MyProductAdapter> {
    Context context;
    List<ProductListDetailI> listcategory;

    public ProductDisplayListAdapter(Context context, List<ProductListDetailI> listcategory) {
        this.context = context;
        this.listcategory = listcategory;
    }

    public void setProductList(List<ProductListDetailI> listcategory) {
        this.listcategory = listcategory;
    }

    @NonNull
    @Override
    public ProductDisplayListAdapter.MyProductAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_page_layout_inflate,parent,false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(orderpage,ProductMainActivity.class);
                orderpage.startActivity(i);*/


            }
        });

        return new ProductDisplayListAdapter.MyProductAdapter (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDisplayListAdapter.MyProductAdapter holder, int position) {

        holder.product_dis_price.setText(listcategory.get(position).getPrice());
        holder.product_description.setText(listcategory.get(position).getDescription());
        holder.product_name.setText(listcategory.get(position).getProductName());
        holder.product_off.setText(listcategory.get(position).getDiscount());
        holder.product_original_price.setText(listcategory.get(position).getMrp());
        Glide.with(context).load(listcategory.get(position).getImg()).into(holder.product_image);

    }

    @Override
    public int getItemCount() {
        return listcategory.size();
    }


    class MyProductAdapter extends RecyclerView.ViewHolder{
        TextView product_name,product_dis_price,product_original_price,product_description,product_off;
        ImageView product_image;
        public MyProductAdapter(View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.design_name);
            product_dis_price = itemView.findViewById(R.id.textView108);
            product_original_price = itemView.findViewById(R.id.textView165);
            product_off = itemView.findViewById(R.id.textView179);
            product_description = itemView.findViewById(R.id.textView10);
            product_image = itemView.findViewById(R.id.item_image);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();

                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){

                        ProductListDetailI clickedDataItem = listcategory.get(pos);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getId(), Toast.LENGTH_SHORT).show();
                       // Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getId(), Toast.LENGTH_SHORT).show()
                        FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.frag_container, new ProductsPageDisplayFragment());
                        transaction.addToBackStack(null);
                        transaction.commit();

                       String c = clickedDataItem.getId();
                        SharedPreferences sp = context.getSharedPreferences("pref",0);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("idvalproduct",c);
                        ed.apply();
                        ed.commit();
                    }
                }
            });
        }
    }

        }





