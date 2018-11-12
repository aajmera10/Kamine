package com.example.xina.kamine.Fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.xina.kamine.Adapter.SelectAddressCardAdapter;
import com.example.xina.kamine.Model.SelectAddressCardModel;
import com.example.xina.kamine.Model.ShowAddressItem;
import com.example.xina.kamine.Model.ShowAddressesModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavedAddresses extends android.support.v4.app.Fragment {

    ConstraintLayout layout_order_summary,layout_ontouch,layout_newAddress,proced_to_payment;
    RecyclerView saved_addresses;
    ImageView backbtn;
    String idno;
    private ProgressDialog mProgressDialog;
    List<ShowAddressItem> listsaves;
    SelectAddressCardAdapter selectAddressCardAdapter;
   SharedPreferences sp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shipment_saved_address,container,false);

        layout_order_summary = view.findViewById(R.id.constraintLayout20);
        layout_ontouch= view.findViewById(R.id.constraintLayout21);
        proced_to_payment= view.findViewById(R.id.constraintLayout19);


        layout_ontouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout_order_summary.isShown()){
                    layout_order_summary.setVisibility(View.GONE);
                }else{
                    layout_order_summary.setVisibility(View.VISIBLE);
                }

            }
        });

        saved_addresses= view.findViewById(R.id.rec_view_savedaddress);
        saved_addresses.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        saved_addresses.setHasFixedSize(true);
        saved_addresses.setNestedScrollingEnabled(false);
        selectAddressCardAdapter = new SelectAddressCardAdapter(getContext(),listsaves);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_offset);
        saved_addresses.addItemDecoration(new SavedAddresses.GridSpacingItemDecoration(1, spacingInPixels, true, 0));
        sp = getContext().getSharedPreferences("pref",0);
        idno = sp.getString("globalD","");

        showProgressDialog();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ShowAddressesModel> call = apiInterface.getshowaddresses(idno);
        call.enqueue(new Callback<ShowAddressesModel>() {
            @Override
            public void onResponse(Call<ShowAddressesModel> call, Response<ShowAddressesModel> response) {
                hideProgressDialog();
                listsaves = response.body().getDetail();
                selectAddressCardAdapter.selectaddresslist(listsaves);
                saved_addresses.setAdapter(selectAddressCardAdapter);
            }

            @Override
            public void onFailure(Call<ShowAddressesModel> call, Throwable t) {
                hideProgressDialog();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void showProgressDialog() {

        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
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
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;
        private int headerNum;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge, int headerNum) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
            this.headerNum = headerNum;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view) - headerNum; // item position

            if (position >= 0) {
                int column = position % spanCount; // item column

                if (includeEdge) {
                    outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                    outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                    if (position < spanCount) { // top edge
                        outRect.top = spacing;
                    }
                    outRect.bottom = spacing; // item bottom
                } else {
                    outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                    outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                    if (position >= spanCount) {
                        outRect.top = spacing; // item top
                    }
                }
            } else {
                outRect.left = 0;
                outRect.right = 0;
                outRect.top = 0;
                outRect.bottom = 0;
            }
        }
    }
}


/*  listsaves = new ArrayList<>();
        listsaves.add(new SelectAddressCardModel("Imean khan","125,D-Block, Near X School","Malviya Nagar, Jaipur",
                "Rajasthan,India","302020","854782652263"));
        listsaves.add(new SelectAddressCardModel("Imean khan","125,D-Block, Near X School","Malviya Nagar, Jaipur",
                "Rajasthan,India","302020","854782652263"));
        listsaves.add(new SelectAddressCardModel("Imean khan","125,D-Block, Near X School","Malviya Nagar, Jaipur",
                "Rajasthan,India","302020","854782652263"));

        SelectAddressCardAdapter listmain = new SelectAddressCardAdapter(getActivity(),listsaves);
        saved_addresses.setAdapter(listmain);*/

//getActivity().onBackPressed();


      /*  layout_newAddress.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                android.app.FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit);
                fragmentTransaction.replace(R.id.frag_container, new AddNewAddress());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });*/