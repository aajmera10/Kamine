package com.example.xina.kamine.Fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xina.kamine.Adapter.SubCategoryDiaplayAdapter;
import com.example.xina.kamine.Model.SubcategoryMainDetail;
import com.example.xina.kamine.Model.SubcategoryMainModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WomenMainDisplay extends android.support.v4.app.Fragment{

    TextView mainname;
    private ProgressDialog mProgressDialog;
    RecyclerView recyclerView;
    String idno,cate;
    SubCategoryDiaplayAdapter recyclerAdapter;
    List<SubcategoryMainDetail> listcategory;
    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_display_page,container,false);
        sp = getContext().getSharedPreferences("Pref",0);
        idno =  sp.getString("first","");
        cate = sp.getString("women","");
        mainname = view.findViewById(R.id.category_name_main);
        recyclerView = view.findViewById(R.id.rec_view_display_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerAdapter = new SubCategoryDiaplayAdapter(getContext(),listcategory);
        mainname.setText(cate);


        showProgressDialog();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SubcategoryMainModel> call = apiInterface.getMainHomeSubcategory(idno);
        call.enqueue(new Callback<SubcategoryMainModel>() {
            @Override
            public void onResponse(Call<SubcategoryMainModel> call, Response<SubcategoryMainModel> response) {
                hideProgressDialog();
                //listcategory = response.body().getSaveAddressDetail();
                listcategory = response.body().getDetail();
                recyclerAdapter.setCategorylist(listcategory);
                recyclerView.setAdapter(recyclerAdapter);

                Toast.makeText(getContext(), "Sucessful", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<SubcategoryMainModel> call, Throwable t) {

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
}
