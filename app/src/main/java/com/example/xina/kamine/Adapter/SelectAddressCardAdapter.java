package com.example.xina.kamine.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.xina.kamine.Model.SelectAddressCardModel;
import com.example.xina.kamine.R;

import java.util.List;

public class SelectAddressCardAdapter extends RecyclerView.Adapter<SelectAddressCardAdapter.MyViewHolderOrder> {


    private Context selectaddressAdapterContext;
    private List<SelectAddressCardModel> selectaddresslist;
    //private final String[] list;
    private int lastCheckedPosition = -1;


    public SelectAddressCardAdapter(Context selectaddressAdapter, List<SelectAddressCardModel> selectaddresslist) {
        this.selectaddressAdapterContext = selectaddressAdapter;
        this.selectaddresslist = selectaddresslist;
        //this.list = list;
    }

    @NonNull
    @Override
    public SelectAddressCardAdapter.MyViewHolderOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(selectaddressAdapterContext);
        View view= inflater.inflate(R.layout.saved_address_design,null);
        return new MyViewHolderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SelectAddressCardAdapter.MyViewHolderOrder holder, int position) {
        SelectAddressCardModel savedaddress = selectaddresslist.get(position);
        holder.name.setText(savedaddress.getCust_name());
        holder.addline1.setText(savedaddress.getAddressline1());
        holder.addressline2.setText(savedaddress.getAddressline2());
        holder.addressline3.setText(savedaddress.getAddressline3());
        holder.phone.setText(savedaddress.getMobileno());
        holder.pincode.setText(savedaddress.getPincode());
        holder.selectAddress.setChecked(position == lastCheckedPosition);
       /* holder.selectAddress.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        holder.color_it_red.setBackgroundColor(Color.parseColor("#800000"));
        holder.selectAddress.setTextColor(Color.parseColor("#ffffff"));
        holder.selectAddress.setHighlightColor(Color.parseColor("#ffffff"));

    }
});*/
        //holder.selectAddress.setChecked(false);

    }

    @Override
    public int getItemCount() {
        return selectaddresslist.size();
    }

    class MyViewHolderOrder extends RecyclerView.ViewHolder{
        TextView name,addline1,addressline2,addressline3,phone,pincode;
        RadioButton selectAddress;
        ConstraintLayout edit,remove,color_it_red;
        public MyViewHolderOrder(View itemView) {
            super(itemView);
           name = itemView.findViewById(R.id.textView80);
            addline1 = itemView.findViewById(R.id.textView93);
            addressline2 = itemView.findViewById(R.id.textView94);
            addressline3 = itemView.findViewById(R.id.textView95);
            phone = itemView.findViewById(R.id.textView98);
            pincode = itemView.findViewById(R.id.textView96);
            selectAddress = itemView.findViewById(R.id.radioButton9);
            edit = itemView.findViewById(R.id.select_address_edit);
            remove = itemView.findViewById(R.id.select_address_remove);
            color_it_red=itemView.findViewById(R.id.constraintLayout22);
           // color_it_red.setBackgroundColor(R.color.mainappcolor);

            selectAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   color_it_red.setBackgroundColor(Color.parseColor("#800000"));
                   selectAddress.setTextColor(Color.parseColor("#ffffff"));
                   selectAddress.setButtonDrawable(R.color.white);
                    lastCheckedPosition = getAdapterPosition();
                    notifyDataSetChanged();

                }
            });

        }
    }
}
