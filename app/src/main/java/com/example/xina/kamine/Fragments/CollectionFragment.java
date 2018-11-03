package com.example.xina.kamine.Fragments;

import android.app.Fragment;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xina.kamine.Adapter.ProductsPageAdapter;
import com.example.xina.kamine.Model.ProductsPageModel;
import com.example.xina.kamine.R;

import java.util.ArrayList;
import java.util.List;

public class CollectionFragment extends android.support.v4.app.Fragment {

    RecyclerView recyclerView1;
    List<ProductsPageModel> productsinpage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collection_main_page,container,false);

        recyclerView1 = view.findViewById(R.id.recview_collectionpage);
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_offset);
        recyclerView1.addItemDecoration(new CollectionFragment.GridSpacingItemDecoration(1, spacingInPixels, true, 0));
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setNestedScrollingEnabled(false);



        productsinpage = new ArrayList<>();

        productsinpage.add(new ProductsPageModel("Kamine","590","750","25","the best design at the price",R.drawable.image));
        productsinpage.add(new ProductsPageModel("Kamine","590","750","25","the best design at the price",R.drawable.im1));
        productsinpage.add(new ProductsPageModel("Kamine","590","750","25","the best design at the price",R.drawable.im3));
        productsinpage.add(new ProductsPageModel("Kamine","590","750","25","the best design at the price",R.drawable.im7));
        productsinpage.add(new ProductsPageModel("Kamine","590","750","25","the best design at the price",R.drawable.im5));
        productsinpage.add(new ProductsPageModel("Kamine","590","750","25","the best design at the price",R.drawable.im4));
        productsinpage.add(new ProductsPageModel("Kamine","590","750","25","the best design at the price",R.drawable.im8));
        productsinpage.add(new ProductsPageModel("Kamine","590","750","25","the best design at the price",R.drawable.im5));
        productsinpage.add(new ProductsPageModel("Kamine","590","750","25","the best design at the price",R.drawable.im4));
        productsinpage.add(new ProductsPageModel("Kamine","590","750","25","the best design at the price",R.drawable.im11));
        productsinpage.add(new ProductsPageModel("Kamine","590","750","25","the best design at the price",R.drawable.im9));



        ProductsPageAdapter ppadapt = new ProductsPageAdapter(getActivity(),productsinpage);
        recyclerView1.setAdapter(ppadapt);


        return view;
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
