package com.example.xina.kamine.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.xina.kamine.Activities.MainHomeActivity;
import com.example.xina.kamine.Adapter.HomeDisplayCategoryAdapter;
import com.example.xina.kamine.Adapter.MainHomeAdapterDisplay;
import com.example.xina.kamine.Adapter.MainHomeSliderAdapter;
import com.example.xina.kamine.Adapter.PicassoImageLoadingService;
import com.example.xina.kamine.Adapter.SubCategoryDiaplayAdapter;
import com.example.xina.kamine.Model.HomeDisplayDetail;
import com.example.xina.kamine.Model.HomeDisplayModel;
import com.example.xina.kamine.Model.HomeDisplayPageModel;
import com.example.xina.kamine.Model.HomeSliderMainDetail;
import com.example.xina.kamine.Model.HomeSliderMainModel;
import com.example.xina.kamine.Model.MainHomeCategoryListModel;
import com.example.xina.kamine.Model.SubcategoryMainDetail;
import com.example.xina.kamine.Model.SubcategoryMainModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ss.com.bannerslider.Slider;
import ss.com.bannerslider.event.OnSlideClickListener;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends android.support.v4.app.Fragment {
        RecyclerView recyclerView_horizontal_display,recyclerView_best_mens,
                recyclerView_best_womens,recyclerView_bestsellers,recyclerView_whats_new,rec_home_subcategory;
        List<HomeDisplayModel>horizontallist,menslist,womenslist,whatsnewlist,bestsellerlist;
        HomeDisplayCategoryAdapter homeDisplayCategoryAdapter;
        String idx;
        List<HomeDisplayDetail> listcategory;

        LinearLayout women,men,matching,best;

        private ProgressDialog mProgressDialog;
        Slider home_slider;
        String id,idm,man,matc,match,sel4,month,idw,woo;


    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_home_fragment,container,false);
        ((MainHomeActivity)getActivity()).showBottom();

            women = view.findViewById(R.id.home_women);
            men = view.findViewById(R.id.home_men);
            matching = view.findViewById(R.id.home_matching);
            best = view.findViewById(R.id.home_clearance);

        home_slider = view.findViewById(R.id.slider_horizontal_display);


        showProgressDialog();
        id = "1";
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<HomeSliderMainModel>call = apiInterface.getMainHomeSlider(id);
        call.enqueue(new Callback<HomeSliderMainModel>() {
            @Override
            public void onResponse(Call<HomeSliderMainModel> call, Response<HomeSliderMainModel> response) {

                hideProgressDialog();
                List<HomeSliderMainDetail> HomeSliderMainModel = response.body().getDetail();

                Slider.init(new PicassoImageLoadingService(getActivity()));
                home_slider.setAdapter(new MainHomeSliderAdapter(getContext(),HomeSliderMainModel));
                home_slider.setOnSlideClickListener(new OnSlideClickListener() {
                    @Override
                    public void onSlideClick(int position) {
                        replaceFragment(new ClosetFragment());
                    }
                });

            }

            @Override
            public void onFailure(Call<HomeSliderMainModel> call, Throwable t) {
                hideProgressDialog();
                Toast.makeText(getContext(), "Unable to Display the Data", Toast.LENGTH_SHORT).show();
            }
        });

        rec_home_subcategory = view.findViewById(R.id.rec_home_subcategory);
        idx = "1";
        //rec_home_subcategory = view.findViewById(R.id.rec_view_display_content);
        rec_home_subcategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rec_home_subcategory.setHasFixedSize(true);
        rec_home_subcategory.setNestedScrollingEnabled(false);
        homeDisplayCategoryAdapter = new HomeDisplayCategoryAdapter(getContext(),listcategory);

        showProgressDialog();

        ApiInterface apiInterfacex = ApiClient.getClient().create(ApiInterface.class);
        Call<HomeDisplayPageModel> callx = apiInterfacex.gethomecategorydisplay(idx);
        callx.enqueue(new Callback<HomeDisplayPageModel>() {
            @Override
            public void onResponse(Call<HomeDisplayPageModel> call, Response<HomeDisplayPageModel> response) {
                hideProgressDialog();
                //listcategory = response.body().getSaveAddressDetail();
                listcategory = response.body().getDetail();
                homeDisplayCategoryAdapter.setCategoryhomelist(listcategory);
                rec_home_subcategory.setAdapter(homeDisplayCategoryAdapter);
               // Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<HomeDisplayPageModel> call, Throwable t) {
                hideProgressDialog();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        /*recyclerView_best_mens = view.findViewById(R.id.recyclerView_best_mens);
        recyclerView_best_mens.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView_best_mens.setHasFixedSize(true);
        recyclerView_best_mens.setNestedScrollingEnabled(false);*/


 /*       recyclerView_best_womens= view.findViewById(R.id.recyclerView_best_womens);
        recyclerView_best_womens.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_best_womens.setHasFixedSize(true);
        recyclerView_best_womens.setNestedScrollingEnabled(false);


        recyclerView_bestsellers=view.findViewById(R.id.recyclerView_bestsellers);
        recyclerView_bestsellers.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_bestsellers.setHasFixedSize(true);
        recyclerView_bestsellers.setNestedScrollingEnabled(false);


       *//* //recyclerView_horizontal_display=view.findViewById(R.id.recyclerView_horizontal_display);
        recyclerView_horizontal_display.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //recyclerView_horizontal_display.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false));
        recyclerView_horizontal_display.setHasFixedSize(true);
        recyclerView_horizontal_display.setNestedScrollingEnabled(false);*//*


        recyclerView_whats_new=view.findViewById(R.id.recyclerView_whats_new);
        recyclerView_whats_new.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_whats_new.setHasFixedSize(true);
        recyclerView_whats_new.setNestedScrollingEnabled(false);*/
       /* menslist = new ArrayList<>();

        menslist.add(new HomeDisplayModel(R.drawable.image,"New Arrivals","new"));
        menslist.add(new HomeDisplayModel(R.drawable.image2,"New Arrivals","new"));
        menslist.add(new HomeDisplayModel(R.drawable.image3,"New Arrivals","new"));


        MainHomeAdapterDisplay mensadapter = new MainHomeAdapterDisplay(getActivity(),menslist);
        recyclerView_best_mens.setAdapter(mensadapter);*/

      /*  womenslist = new ArrayList<>();

        womenslist.add(new HomeDisplayModel(R.drawable.image,"New Arrivals"," "));
        womenslist.add(new HomeDisplayModel(R.drawable.image2,"New Arrivals"," "));
        womenslist.add(new HomeDisplayModel(R.drawable.image3,"New Arrivals","new"));

        MainHomeAdapterDisplay womensadapter = new MainHomeAdapterDisplay(getActivity(),womenslist);
        recyclerView_best_womens.setAdapter(womensadapter);*/
/*
        bestsellerlist = new ArrayList<>();

        bestsellerlist.add(new HomeDisplayModel(R.drawable.image,"New Arrivals"," "));
        bestsellerlist.add(new HomeDisplayModel(R.drawable.image2,"New Arrivals"," "));
        bestsellerlist.add(new HomeDisplayModel(R.drawable.image3,"New Arrivals","new"));

        MainHomeAdapterDisplay bestseller = new MainHomeAdapterDisplay(getActivity(),bestsellerlist);
        recyclerView_bestsellers.setAdapter(bestseller);*/

      /*  whatsnewlist = new ArrayList<>();

        whatsnewlist.add(new HomeDisplayModel(R.drawable.image,"New Arrivals"," "));
        whatsnewlist.add(new HomeDisplayModel(R.drawable.image2,"New Arrivals"," "));
        whatsnewlist.add(new HomeDisplayModel(R.drawable.image3,"New Arrivals","new"));

        MainHomeAdapterDisplay whatnewadapter = new MainHomeAdapterDisplay(getActivity(),whatsnewlist);
        recyclerView_whats_new.setAdapter(whatnewadapter);*/



     women.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
              idw = "2";
              woo = "Women";
             sp = getActivity().getSharedPreferences("Pref",0);
             SharedPreferences.Editor eg = sp.edit();
             eg.putString("first",idw);
             eg.putString("women",woo);
             eg.apply();
             showProgressDialog();

             ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
             Call<MainHomeCategoryListModel>call = apiInterface.getMainHomeCategory(idw,woo);
             call.enqueue(new Callback<MainHomeCategoryListModel>() {
                 @Override
                 public void onResponse(Call<MainHomeCategoryListModel> call, Response<MainHomeCategoryListModel> response) {
                     if (response.body().getSuccess().equals("200")){
                         hideProgressDialog();
                         //Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                         replaceFragment(new WomenMainDisplay());
                         //String c  = response.body().getClass().getName();
                         //Toast.makeText(getContext(), "women", Toast.LENGTH_SHORT).show();
                         //Toast.makeText(getContext(), c, Toast.LENGTH_SHORT).show();
                         Toast.makeText(getContext(),response.body().getSuccess(),Toast.LENGTH_SHORT).show();

                     }
                 }
                 @Override
                 public void onFailure(Call<MainHomeCategoryListModel> call, Throwable t) {

                    hideProgressDialog();
                    Toast.makeText(getContext(),"oops", Toast.LENGTH_SHORT).show();
                 }
             });
         }
     });

     men.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            idm = "1";
              man = "Men";
             sp = getActivity().getSharedPreferences("Pref",0);
             SharedPreferences.Editor eg = sp.edit();
             eg.putString("second",idm);
             eg.putString("men",man);
             eg.apply();
             showProgressDialog();

             ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
             Call<MainHomeCategoryListModel>call = apiInterface.getMainHomeCategory(idm,man);
             call.enqueue(new Callback<MainHomeCategoryListModel>() {
                 @Override
                 public void onResponse(Call<MainHomeCategoryListModel> call, Response<MainHomeCategoryListModel> response) {
                     if (response.body().getSuccess().equals("200")){
                         hideProgressDialog();
                         replaceFragment(new ManMainDiaplay());
                                               //Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                         Toast.makeText(getContext(), "men", Toast.LENGTH_SHORT).show();


                     }
                 }

                 @Override
                 public void onFailure(Call<MainHomeCategoryListModel> call, Throwable t) {

                     hideProgressDialog();
                     Toast.makeText(getContext(),"oops", Toast.LENGTH_SHORT).show();
                 }
             });
         }
     });
        matching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 matc = "3";
                 match = "Matching T-shirts";
                sp = getActivity().getSharedPreferences("Pref",0);
                SharedPreferences.Editor eg = sp.edit();
                eg.putString("third",matc);
                eg.putString("matching",match);
                eg.apply();
                showProgressDialog();

                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<MainHomeCategoryListModel>call = apiInterface.getMainHomeCategory(matc,match);
                call.enqueue(new Callback<MainHomeCategoryListModel>() {
                    @Override
                    public void onResponse(Call<MainHomeCategoryListModel> call, Response<MainHomeCategoryListModel> response) {
                        if (response.body().getSuccess().equals("200")){
                            hideProgressDialog();
                            replaceFragment(new MatchingMainDisplay());

                            Toast.makeText(getContext(), "matching", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<MainHomeCategoryListModel> call, Throwable t) {

                        hideProgressDialog();
                        Toast.makeText(getContext(),"oops", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        best.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sel4 = "4";
               month = "T-shirt of the month";
                replaceFragment(new TShirtofMonthDisplay());
                sp = getActivity().getSharedPreferences("Pref",0);
                SharedPreferences.Editor eg = sp.edit();
                eg.putString("forth",sel4);
                eg.putString("forthname",month);
                eg.apply();
                showProgressDialog();

                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<MainHomeCategoryListModel>call = apiInterface.getMainHomeCategory(sel4,month);
                call.enqueue(new Callback<MainHomeCategoryListModel>() {
                    @Override
                    public void onResponse(Call<MainHomeCategoryListModel> call, Response<MainHomeCategoryListModel> response) {
                        if (response.body().getSuccess().equals("200")){
                            hideProgressDialog();
                            replaceFragment(new TShirtofMonthDisplay());
                            //Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(), "Tshirt", Toast.LENGTH_SHORT).show();


                        }
                    }

                    @Override
                    public void onFailure(Call<MainHomeCategoryListModel> call, Throwable t) {

                        hideProgressDialog();
                        Toast.makeText(getContext(),"oops", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;

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



}





