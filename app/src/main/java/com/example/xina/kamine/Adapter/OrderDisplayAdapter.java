package com.example.xina.kamine.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xina.kamine.Fragments.ClosetFragment;
import com.example.xina.kamine.Fragments.MyOrders;
import com.example.xina.kamine.Fragments.MyOrdersDetails;
import com.example.xina.kamine.Model.HomeDisplayModel;
import com.example.xina.kamine.Model.OrderDisplayModel;
import com.example.xina.kamine.Model.OrderTextList;
import com.example.xina.kamine.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderDisplayAdapter extends RecyclerView.Adapter<OrderDisplayAdapter.MyOrigi>{


    private Context orderdiaplay;
    private List<OrderDisplayModel> orderMain;
    //private List<OrderTextList> textlist;



    public OrderDisplayAdapter(Context orderdiaplay, List<OrderDisplayModel> orderMain) {
        this.orderdiaplay = orderdiaplay;
        this.orderMain = orderMain;
    }

    @NonNull
    @Override
    public OrderDisplayAdapter.MyOrigi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.your_orders,parent,false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = ((AppCompatActivity)orderdiaplay).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frag_container, new MyOrdersDetails());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return new MyOrigi(view);
/*
        final RecyclerView.ViewHolder holder;
        View view;

        *//*switch (viewType){
            case R.layout.order_detail_list_view:
                view = LayoutInflater.from(orderdiaplay).inflate(R.layout.order_detail_list_view,parent,false);
                holder = new Mytextview(view);
                break;

            case R.layout.your_orders:
                view = LayoutInflater.from(orderdiaplay).inflate(R.layout.your_orders,parent,false);
                holder = new MyOrigi(view);
                break;
            default:
                view = LayoutInflater.from(orderdiaplay).inflate(R.layout.your_orders,parent,false);
                holder = new MyOrigi(view);
        }

        return holder;*/
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDisplayAdapter.MyOrigi holder, int position) {
        List<OrderTextList> list = new ArrayList<>(Arrays.asList(orderMain.get(position).getAllItemsInSection()));

        //ArrayList singletextitem = orderMain.get(position).getAllItemsInSection();
        //String textorder = orderMain.get(position)

        OrderListView orderx = new OrderListView(orderdiaplay,list);


        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(orderdiaplay, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setLayoutFrozen(true);
        holder.recyclerView.setAdapter(orderx);


        OrderDisplayModel order = orderMain.get(position);
        holder.ordertotal.setText(order.getOrder_total());
        holder.orderdate.setText(order.getOrder_date());
        holder.orderid.setText(order.getOrder_id());
        holder.order_del_not.setText(order.getSucessful_d_or_not());


    }

    @Override
    public int getItemCount() {
        return orderMain.size();
    }

    class MyOrigi extends RecyclerView.ViewHolder {

        TextView orderdate,ordertotal,orderid,order_del_not;
        RecyclerView recyclerView;


        public MyOrigi(View itemView) {
            super(itemView);
           // recyclerView = itemView.findViewById(R.id.list);
            orderdate= itemView.findViewById(R.id.textView33);
            orderid= itemView.findViewById(R.id.textView35);
            ordertotal= itemView.findViewById(R.id.textView37);
            order_del_not= itemView.findViewById(R.id.textView39);
            recyclerView = itemView.findViewById(R.id.recyclerViewoftext);


        }
    }

}
