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
import android.widget.Toast;

import com.example.xina.kamine.Model.SelectAddressCardModel;
import com.example.xina.kamine.Model.ShowAddressItem;
import com.example.xina.kamine.Model.SubcategoryMainDetail;
import com.example.xina.kamine.R;

import java.util.ArrayList;
import java.util.List;

public class SelectAddressCardAdapter extends RecyclerView.Adapter<SelectAddressCardAdapter.MyViewHolderOrder> {


    private Context selectaddressAdapterContext;
    private List<ShowAddressItem> selectaddresslist;
    private int mSelectedItem = 0;

    public void selectaddresslist(List<ShowAddressItem> selectaddresslist) {
        this.selectaddresslist = selectaddresslist;
    }
    public SelectAddressCardAdapter(Context selectaddressAdapter, List<ShowAddressItem> selectaddresslist) {
        this.selectaddressAdapterContext = selectaddressAdapter;
        this.selectaddresslist = selectaddresslist;
    }

    @NonNull
    @Override
    public SelectAddressCardAdapter.MyViewHolderOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(selectaddressAdapterContext);
        View view= inflater.inflate(R.layout.saved_address_design,null);
        return new MyViewHolderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SelectAddressCardAdapter.MyViewHolderOrder holder, final int position) {
        ShowAddressItem savedaddress = selectaddresslist.get(position);
        String x = savedaddress.getFirstname();
        String c = savedaddress.getLastname();
        String z = x+" "+c;
        holder.name.setText(z);
        holder.addressline.setText(savedaddress.getAddress());
        holder.mobile.setText(savedaddress.getMobile());
        holder.pincode.setText(savedaddress.getPincode());
        holder.country.setText(savedaddress.getCountry());
        holder.city.setText(savedaddress.getCity());
        holder.state.setText(savedaddress.getState());
        holder.landmark.setText(savedaddress.getLandmark());
        holder.selectAddress.setChecked(mSelectedItem == position);

    }

    @Override
    public int getItemCount() {
        return selectaddresslist.size();
    }

    class MyViewHolderOrder extends RecyclerView.ViewHolder{
        TextView name,addressline,landmark,state,city,pincode,mobile,country;
        RadioButton selectAddress;
        public MyViewHolderOrder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView80);
            addressline = itemView.findViewById(R.id.textView93);
            pincode = itemView.findViewById(R.id.textView96);
            selectAddress = itemView.findViewById(R.id.radioButton9);
            landmark = itemView.findViewById(R.id.landmark_show);
            city = itemView.findViewById(R.id.city_show_name);
            state = itemView.findViewById(R.id.textView95);
            mobile = itemView.findViewById(R.id.textView98);
            country = itemView.findViewById(R.id.x86);

            selectAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyDataSetChanged();
                    ShowAddressItem clickedDataItem = selectaddresslist.get(mSelectedItem);
                    Toast.makeText(selectaddressAdapterContext,clickedDataItem.getId(), Toast.LENGTH_SHORT).show();
                }
            });

        }
        }
}
