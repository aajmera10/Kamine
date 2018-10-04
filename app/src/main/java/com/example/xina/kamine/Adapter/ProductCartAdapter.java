package com.example.xina.kamine.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xina.kamine.Model.CartProductModel;
import com.example.xina.kamine.R;

import java.util.List;

public class ProductCartAdapter extends RecyclerView.Adapter<ProductCartAdapter.MyViewHolder> {
private Context context_cartAdapter;
private List<CartProductModel> list_cart_model;

    public ProductCartAdapter(Context context_cartAdapter, List<CartProductModel> list_cart_model) {
        this.context_cartAdapter = context_cartAdapter;
        this.list_cart_model = list_cart_model;
    }

    @NonNull
    @Override
    public ProductCartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context_cartAdapter);
        View view= inflater.inflate(R.layout.cart_product_card,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCartAdapter.MyViewHolder holder, int position) {
        CartProductModel cartlist = list_cart_model.get(position);
        holder.product_image.setImageDrawable(context_cartAdapter.getResources().getDrawable(cartlist.getProduct_image()));
        holder.product_name.setText(cartlist.getProduct_name());
        holder.product_price.setText(cartlist.getProduct_price());
        holder.product_original_price.setText(cartlist.getProduct_actual_price());
        holder.product_color.setText(cartlist.getProduct_colortype());
        holder.product_discount.setText(cartlist.getProduct_discount());
        holder.product_size.setText(cartlist.getProduct_size());
        holder.remove_from_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list_cart_model.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView product_name,product_price,product_original_price,product_discount,product_color,product_size,product_quantity;
        ImageView product_image;
        ConstraintLayout add_to_wishlist,remove_from_cart;

        public MyViewHolder(View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.imageView28);
            product_name = itemView.findViewById(R.id.textView115);
            product_price= itemView.findViewById(R.id.textView116);
            product_original_price= itemView.findViewById(R.id.textView122);
            product_discount = itemView.findViewById(R.id.textView127);
            product_color = itemView.findViewById(R.id.textView137);
            product_size= itemView.findViewById(R.id.textView138);
            product_quantity= itemView.findViewById(R.id.textView139);

            add_to_wishlist = itemView.findViewById(R.id.constraintLayout30);
            remove_from_cart = itemView.findViewById(R.id.constraintLayout31);
        }
    }
}
