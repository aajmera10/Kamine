package com.example.xina.kamine.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
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

import com.example.xina.kamine.Activities.MainHomeActivity;
import com.example.xina.kamine.Fragments.ClosetFragment;
import com.example.xina.kamine.Fragments.ProductsPageDisplayFragment;
import com.example.xina.kamine.Model.HomeDisplayModel;
import com.example.xina.kamine.Model.ProductsPageModel;
import com.example.xina.kamine.R;

import java.util.List;

public class ProductsPageAdapter extends RecyclerView.Adapter<ProductsPageAdapter.MyProductAdapter> {

    private Context orderpage;
    private List<ProductsPageModel> productmodel ;

    public ProductsPageAdapter(Context orderpage, List<ProductsPageModel> productmodel) {
        this.orderpage = orderpage;
        this.productmodel = productmodel;
    }


    @NonNull
    @Override
    public ProductsPageAdapter.MyProductAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(orderpage);
        View view= inflater.inflate(R.layout.product_page_layout_inflate,null);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(orderpage,ProductMainActivity.class);
                orderpage.startActivity(i);*/

                FragmentManager manager = ((AppCompatActivity)orderpage).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frag_container, new ProductsPageDisplayFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return new MyProductAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsPageAdapter.MyProductAdapter holder, int position) {

        ProductsPageModel showproduct = productmodel.get(position);
        holder.product_name.setText(showproduct.getProduct_name());
        holder.product_original_price.setText(showproduct.getProduct_actual_price());
        holder.product_dis_price.setText(showproduct.getProduct_price());
        holder.product_description.setText(showproduct.getProduct_description());
        holder.product_off.setText(showproduct.getProduct_discount());
        holder.product_image.setImageDrawable(orderpage.getResources().getDrawable(showproduct.getProduct_main_image()));

    }

    @Override
    public int getItemCount() {
        return productmodel.size();
    }

    class MyProductAdapter extends RecyclerView.ViewHolder{
        TextView product_name,product_dis_price,product_original_price,product_description,product_off;
        ImageView product_image;
        public MyProductAdapter(View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.design_name);
            product_dis_price = itemView.findViewById(R.id.textView12);
            product_original_price = itemView.findViewById(R.id.textView13);
            product_off = itemView.findViewById(R.id.textView14);
            product_description = itemView.findViewById(R.id.textView10);
            product_image = itemView.findViewById(R.id.item_image);

        }
    }


}
