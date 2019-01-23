package com.example.xina.kamine.Fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xina.kamine.Adapter.ProductDisplayListAdapter;
import com.example.xina.kamine.Adapter.ProductsPageAdapter;
import com.example.xina.kamine.Model.ProductListDetailI;
import com.example.xina.kamine.Model.ProductListModel;
import com.example.xina.kamine.Model.SubcategoryMainDetail;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListDisplayFragment extends Fragment {
    RecyclerView recview;
    SharedPreferences sp;
    private ProgressDialog mProgressDialog;
    TextView tv;
    String text,idx;
    ProductDisplayListAdapter recviewadapter;
    List<ProductListDetailI> listcategory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.product_list_display,container,false);
        tv= view.findViewById(R.id.popinsBold1011);
        recview = view.findViewById(R.id.productrec);
        recview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_offset);
        recview.addItemDecoration(new ProductListDisplayFragment.GridSpacingItemDecoration(2, spacingInPixels, false, 0));
        recview.setHasFixedSize(true);
        recview.setNestedScrollingEnabled(false);
        recviewadapter = new ProductDisplayListAdapter(getContext(),listcategory);

        sp = getActivity().getSharedPreferences("pref",0);
        idx = sp.getString("idvallist","");
        text = sp.getString("idnamelist","");
        tv.setText(text);

        showProgressDialog();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductListModel> call = apiInterface.getProductList(idx);
        call.enqueue(new Callback<ProductListModel>() {
            @Override
            public void onResponse(Call<ProductListModel> call, Response<ProductListModel> response) {
                hideProgressDialog();
                if(response.body().getSuccess()==200){
                    listcategory = response.body().getDetail();
                    recviewadapter.setProductList(listcategory);
                    recview.setAdapter(recviewadapter);
                    Toast.makeText(getContext(), "whoppie", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductListModel> call, Throwable t) {
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

          //  int a = 5-8*8+(55%2);
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
    }
}
