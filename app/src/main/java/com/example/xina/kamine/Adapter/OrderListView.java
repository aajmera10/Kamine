package com.example.xina.kamine.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xina.kamine.Model.HomeDisplayModel;
import com.example.xina.kamine.Model.OrderDisplayModel;
import com.example.xina.kamine.Model.OrderTextList;
import com.example.xina.kamine.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class OrderListView extends RecyclerView.Adapter<OrderListView.MyViewHolder>{

    private Context orderlist_small;
    private List<OrderTextList> order_small_list ;


    public OrderListView(Context orderlist_small, List<OrderTextList> order_small_list) {
        this.orderlist_small = orderlist_small;
        this.order_small_list = order_small_list;
    }

    @NonNull

    @Override
    public OrderListView.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(orderlist_small);
        View view= inflater.inflate(R.layout.order_detail_list_view,null);

        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull OrderListView.MyViewHolder holder, int position) {

        OrderTextList textList = order_small_list.get(position);
        holder.orderdetail_1line.setImageDrawable(orderlist_small.getResources().getDrawable(textList.getImg()));
        holder.orderdetail_1line2.setImageDrawable(orderlist_small.getResources().getDrawable(textList.getImg1()));
        holder.orderdetail_1line3.setImageDrawable(orderlist_small.getResources().getDrawable(textList.getImg2()));
        holder.orderdetail_1line4.setImageDrawable(orderlist_small.getResources().getDrawable(textList.getImg3()));
        holder.orderdetail_1line5.setImageDrawable(orderlist_small.getResources().getDrawable(textList.getImg4()));
    }
    @Override
    public int getItemCount() {
        return order_small_list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView orderdetail_1line,orderdetail_1line2,orderdetail_1line3,orderdetail_1line4,orderdetail_1line5;
        public MyViewHolder(View itemView) {
            super(itemView);
            orderdetail_1line = itemView.findViewById(R.id.orderdetail_1line1);
            orderdetail_1line2 = itemView.findViewById(R.id.orderdetail_1line2);
            orderdetail_1line3 = itemView.findViewById(R.id.orderdetail_1line3);
            orderdetail_1line4 = itemView.findViewById(R.id.orderdetail_1line4);
            orderdetail_1line5 = itemView.findViewById(R.id.orderdetail_1line5);
        }
    }
}
