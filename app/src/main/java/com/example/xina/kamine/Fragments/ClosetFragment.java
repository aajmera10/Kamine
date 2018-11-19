package com.example.xina.kamine.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xina.kamine.Activities.MainHomeActivity;
import com.example.xina.kamine.Adapter.ProductDisplayListAdapter;
import com.example.xina.kamine.Adapter.ProductsPageAdapter;
import com.example.xina.kamine.Adapter.ShowWishlistAdapter;
import com.example.xina.kamine.Model.ProductListDetailI;
import com.example.xina.kamine.Model.ProductsPageModel;
import com.example.xina.kamine.Model.ShowWishlistDetail;
import com.example.xina.kamine.Model.ShowWishlistModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClosetFragment extends android.support.v4.app.Fragment {


    SharedPreferences sp;
    private ProgressDialog mProgressDialog;
    TextView tv;
    String text,idx;
    ShowWishlistAdapter showWishlistAdapter;
    List<ShowWishlistDetail> listcategory;
    RecyclerView recyclerView1;

    // Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.closet_main, container, false);
        ((MainHomeActivity) getActivity()).showBottom();
        sp = getActivity().getSharedPreferences("pref",0);
            idx = sp.getString("globalD","");
            text = sp.getString("globalname","");
            recyclerView1 = view.findViewById(R.id.rec_closet);

            if(sp.getString("globalD","").isEmpty()||sp.getString("globalD","").isEmpty()){




            }else{
                recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_offset);
                recyclerView1.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, false, 0));
                recyclerView1.setHasFixedSize(true);
                recyclerView1.setNestedScrollingEnabled(false);
                showWishlistAdapter = new ShowWishlistAdapter(getContext(),listcategory);
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<ShowWishlistModel> call = apiInterface.getwishlist(idx);
                call.enqueue(new Callback<ShowWishlistModel>() {
                    @Override
                    public void onResponse(Call<ShowWishlistModel> call, Response<ShowWishlistModel> response) {
                        listcategory = response.body().getDetail();
                        showWishlistAdapter.setwish(listcategory);
                        recyclerView1.setAdapter(showWishlistAdapter);
                        hideProgressDialog();
                    }

                    @Override
                    public void onFailure(Call<ShowWishlistModel> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });




            }



        /*recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_offset);
        recyclerView1.addItemDecoration(new GridSpacingItemDecoration(1, spacingInPixels, true, 0));
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setNestedScrollingEnabled(false);
*/

      /*  productsinpage = new ArrayList<>();

        productsinpage.add(new ProductsPageModel("Kamine","Rs.590","Rs.750","25% off","the best design at the price",R.drawable.image));
        productsinpage.add(new ProductsPageModel("Kamine","Rs.590","RS.750","25% off","the best design at the price",R.drawable.im1));
        productsinpage.add(new ProductsPageModel("Kamine","Rs.590","RS.750","25% off","the best design at the price",R.drawable.im3));
        productsinpage.add(new ProductsPageModel("Kamine","Rs.590","RS.750","25% off","the best design at the price",R.drawable.im7));
        productsinpage.add(new ProductsPageModel("Kamine","Rs.590","RS.750","25% off","the best design at the price",R.drawable.im5));
        productsinpage.add(new ProductsPageModel("Kamine","Rs.590","RS.750","25% off","the best design at the price",R.drawable.im4));
        productsinpage.add(new ProductsPageModel("Kamine","Rs.590","RS.750","25% off","the best design at the price",R.drawable.im8));
        productsinpage.add(new ProductsPageModel("Kamine","Rs.590","RS.750","25% off","the best design at the price",R.drawable.im5));
        productsinpage.add(new ProductsPageModel("Kamine","Rs.590","RS.750","25% off","the best design at the price",R.drawable.im4));
        productsinpage.add(new ProductsPageModel("Kamine","Rs.590","RS.750","25% off","the best design at the price",R.drawable.im11));
        productsinpage.add(new ProductsPageModel("Kamine","Rs.590","RS.750","25% off","the best design at the price",R.drawable.im9));
        productsinpage.add(new ProductsPageModel("Kamine","Rs.590","RS.750","25% off","the best design at the price",R.drawable.im9));



        ProductsPageAdapter ppadapt = new ProductsPageAdapter(getActivity(), productsinpage);
        recyclerView1.setAdapter(ppadapt);
*/

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

    private void replaceFragment(android.support.v4.app.Fragment f) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        /*transaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit);*/
        //bottomNavigationView.setVisibility(View.VISIBLE);
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();

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
    }}
