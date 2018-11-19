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
import com.example.xina.kamine.Fragments.ProductsPageDisplayFragment;
import com.example.xina.kamine.Model.ProductListDetailI;
import com.example.xina.kamine.Model.ShowWishlistDetail;
import com.example.xina.kamine.R;

import java.util.List;

public class ShowWishlistAdapter extends RecyclerView.Adapter<ShowWishlistAdapter.MyProductAdapter> {
Context context;
List<ShowWishlistDetail> showWishlistDetails;

    public ShowWishlistAdapter(Context context, List<ShowWishlistDetail> showWishlistDetails) {
        this.context = context;
        this.showWishlistDetails = showWishlistDetails;
    }

    public void setwish(List<ShowWishlistDetail> showWishlistDetails) {
        this.showWishlistDetails = showWishlistDetails;
    }

    @NonNull
    @Override
    public ShowWishlistAdapter.MyProductAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_page_layout_inflate,parent,false);
        return new ShowWishlistAdapter.MyProductAdapter (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowWishlistAdapter.MyProductAdapter holder, int position) {

        holder.product_dis_price.setText(showWishlistDetails.get(position).getPrice());
        holder.product_description.setText(showWishlistDetails.get(position).getDescription());
        holder.product_name.setText(showWishlistDetails.get(position).getProductName());
        holder.product_off.setText(showWishlistDetails.get(position).getDiscount());
        holder.product_original_price.setText(showWishlistDetails.get(position).getMrp());
        Glide.with(context).load(showWishlistDetails.get(position).getImage()).into(holder.product_image);
    }

    @Override
    public int getItemCount() {
        return showWishlistDetails.size();
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

                        ShowWishlistDetail clickedDataItem = showWishlistDetails.get(pos);
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
