package com.example.xina.kamine.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xina.kamine.Model.ProductCardModel;
import com.example.xina.kamine.Model.ProductsPageModel;
import com.example.xina.kamine.R;

import java.util.List;

public class ProductCardViewAdapter extends RecyclerView.Adapter<ProductCardViewAdapter.MyViewAdapter> {

    private Context productcardview;
    private List<ProductCardModel> productcardviewmodel ;

    public ProductCardViewAdapter(Context productcardview, List<ProductCardModel> productcardviewmodel) {
        this.productcardview = productcardview;
        this.productcardviewmodel = productcardviewmodel;
    }


    @NonNull
    @Override
    public ProductCardViewAdapter.MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(productcardview);
        View view= inflater.inflate(R.layout.inside_product_detail_view,null);
        return new MyViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewAdapter.MyViewAdapter holder, int position) {
        ProductCardModel card = productcardviewmodel.get(position);
        holder.productimage.setImageDrawable(productcardview.getResources().getDrawable(card.getProduct_image()));
        holder.product_name.setText(card.getProduct_name());
        holder.product_price.setText(card.getProduct_price());
        holder.product_original_price.setText(card.getProduct_actual_price());
        holder.product_discount.setText(card.getProduct_discount());
        holder.product_color.setText(card.getProduct_colortype());
        holder.product_size.setText(card.getProduct_size());
        holder.product_quantity.setText(card.getProduct_quantity());
        holder.product_delivered.setText(card.getProduct_delivered());

    }

    @Override
    public int getItemCount() {
        return productcardviewmodel.size();
    }

    class MyViewAdapter extends RecyclerView.ViewHolder{
            ImageView productimage;
            TextView product_name,product_price,product_original_price,product_discount,
                    product_color,product_size,product_quantity,product_delivered;
        public MyViewAdapter(View itemView) {
            super(itemView);

            productimage = itemView.findViewById(R.id.imageView17);
            product_name = itemView.findViewById(R.id.textView46);
            product_price = itemView.findViewById(R.id.textView47);
            product_original_price = itemView.findViewById(R.id.textView59);
            product_discount = itemView.findViewById(R.id.textView60);
            product_color = itemView.findViewById(R.id.textView61);
            product_size = itemView.findViewById(R.id.textView62);
            product_quantity = itemView.findViewById(R.id.textView63);
            product_delivered = itemView.findViewById(R.id.textView112);
        }
    }
}
