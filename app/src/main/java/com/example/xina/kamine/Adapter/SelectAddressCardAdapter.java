package com.example.xina.kamine.Adapter;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xina.kamine.Fragments.AddNewAddress;
import com.example.xina.kamine.Fragments.EditAddressFragment;
import com.example.xina.kamine.Fragments.ProductListDisplayFragment;
import com.example.xina.kamine.Model.DeleteAddressModel;
import com.example.xina.kamine.Model.EditAddressModel;
import com.example.xina.kamine.Model.LogoutModel;
import com.example.xina.kamine.Model.SelectAddressCardModel;
import com.example.xina.kamine.Model.ShowAddressItem;
import com.example.xina.kamine.Model.SubcategoryMainDetail;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

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
        ConstraintLayout edit,remove;
        RadioButton selectAddress;
        SharedPreferences sp;

        private ProgressDialog mProgressDialog;
        public MyViewHolderOrder(final View itemView) {
            super(itemView);
            edit = itemView.findViewById(R.id.select_address_edit);
            remove = itemView.findViewById(R.id.select_address_remove);
            name = itemView.findViewById(R.id.textView80);
            addressline = itemView.findViewById(R.id.textView93);
            pincode = itemView.findViewById(R.id.textView96);
            selectAddress = itemView.findViewById(R.id.radioButton9);
            landmark = itemView.findViewById(R.id.landmark_show);
            city = itemView.findViewById(R.id.city_show_name);
            state = itemView.findViewById(R.id.textView95);
            mobile = itemView.findViewById(R.id.textView98);
            country = itemView.findViewById(R.id.x86);
            final ShowAddressItem clickedDataItem = selectaddresslist.get(mSelectedItem);

            selectAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyDataSetChanged();
                    ShowAddressItem clickedDataItem = selectaddresslist.get(mSelectedItem);
                    Toast.makeText(selectaddressAdapterContext,clickedDataItem.getId(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(selectaddressAdapterContext,clickedDataItem.getUserid(), Toast.LENGTH_SHORT).show();
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(selectaddressAdapterContext);
                    builder.setTitle("Want to Delete This Address?");
                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showProgressDialog();
                            String cc = clickedDataItem.getId();
                            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                            Call<DeleteAddressModel> call = apiInterface.getdeleteaddress(cc);
                            call.enqueue(new Callback<DeleteAddressModel>() {
                                @Override
                                public void onResponse(Call<DeleteAddressModel> call, Response<DeleteAddressModel> response) {
                                    if (response.body().getSuccess() == 200){
                                        mSelectedItem = getAdapterPosition();
                                        removeAt(mSelectedItem);
                                        Toast.makeText(selectaddressAdapterContext, "Deleted Address Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                    hideProgressDialog();
                                }
                                @Override
                                public void onFailure(Call<DeleteAddressModel> call, Throwable t) {
                                   hideProgressDialog();
                                   Toast.makeText(selectaddressAdapterContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            dialog.cancel();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mSelectedItem = getAdapterPosition();
                    notifyDataSetChanged();
                    ShowAddressItem clickedDataItem = selectaddresslist.get(mSelectedItem);
                    String firstname= clickedDataItem.getFirstname();
                    String Lastname= clickedDataItem.getLastname();
                    String Address= clickedDataItem.getLastname();
                    String City= clickedDataItem.getCity();
                    String Country= clickedDataItem.getCountry();
                    String Landmark= clickedDataItem.getLandmark();
                    String Mobile= clickedDataItem.getMobile();
                    String Id= clickedDataItem.getId();
                    String State= clickedDataItem.getState();
                    String Userid= clickedDataItem.getUserid();
                    String pincodex= clickedDataItem.getPincode();
                    sp = selectaddressAdapterContext.getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor eg = sp.edit();
                    eg.putString("add_id",clickedDataItem.getId());
                    eg.putBoolean("editAddress",true);
                    eg.putString("edt_firstname",firstname);
                    eg.putString("edt_lastname",Lastname);
                    eg.putString("edt_address",Address);
                    eg.putString("edt_city",City);
                    eg.putString("edt_country",Country);
                    eg.putString("edt_landmark",Landmark);
                    eg.putString("edt_mobile",Mobile);
                    eg.putString("edt_id",Id);
                    eg.putString("edt_state",State);
                    eg.putString("edt_id",Userid);
                    eg.putString("edt_pincode",pincodex);
                    eg.apply();


                    EditAddressFragment editAddressFragment = new EditAddressFragment();

                    Bundle b = new Bundle();

                    editAddressFragment.setArguments(b);

                    android.support.v4.app.FragmentManager manager = ((AppCompatActivity)selectaddressAdapterContext).getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.frag_container, new EditAddressFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();


                }
            });

        }

        private void showProgressDialog() {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(selectaddressAdapterContext);
                mProgressDialog.setMessage("loading...");
                mProgressDialog.setIndeterminate(true);
            }

            mProgressDialog.show();
        }

        private void hideProgressDialog() {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.hide();
            }


        }
        public void removeAt(int position) {
            selectaddresslist.remove(position);
            notifyItemRemoved(position);
            notifyItemChanged(position);
            notifyItemRangeRemoved(position, 1);
        }
        }
}
