package com.example.xina.kamine.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xina.kamine.Activities.MainHomeActivity;
import com.example.xina.kamine.Adapter.MainHomeAdapterDisplay;
import com.example.xina.kamine.Adapter.OrderDisplayAdapter;
import com.example.xina.kamine.Model.OrderDisplayModel;
import com.example.xina.kamine.Model.OrderTextList;
import com.example.xina.kamine.R;

import java.util.ArrayList;
import java.util.List;

public class MyOrders extends Fragment {

    RecyclerView recyclerView_main,recyclerView_text;
    List<OrderDisplayModel> mainlist,second_mainlist,third_mainlist;
    List<OrderTextList> textlist,second_textlist,third_textlist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_orders_main_screen,container,false);
       // ((MainHomeActivity)getActivity()).removeBottom();

        recyclerView_main = view.findViewById(R.id.rec_view_main);
        recyclerView_main.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_main.setHasFixedSize(true);
        recyclerView_main.setNestedScrollingEnabled(false);

        textlist = new ArrayList<>();
        /*textlist.add(new OrderTextList("sdiefbiuwfnwjkefnwn"));
        textlist.add(new OrderTextList("sdiefbiuwfnwjkefnwn"));
        textlist.add(new OrderTextList("sdiefbiuwfnwjkefnwn"));
        textlist.add(new OrderTextList("sdiefbiuwfnwjkefnwn"));

*/
        textlist.add(new OrderTextList(R.drawable.image,R.drawable.image2,R.drawable.image3,R.drawable.image2,R.drawable.image3));
        textlist.add(new OrderTextList(R.drawable.image,R.drawable.image2,R.drawable.image3,R.drawable.image2,R.drawable.image3));
        textlist.add(new OrderTextList(R.drawable.image,R.drawable.image2,R.drawable.image3,R.drawable.image2,R.drawable.image3));
        textlist.add(new OrderTextList(R.drawable.image,R.drawable.image2,R.drawable.image3,R.drawable.image2,R.drawable.image3));
        textlist.add(new OrderTextList(R.drawable.image,R.drawable.image2,R.drawable.image3,R.drawable.image2,R.drawable.image3));

        mainlist = new ArrayList<>();
        mainlist.add(new OrderDisplayModel("16.08.2015","552616515","Rs.1535","Yout Order willbe Deliverd by 26 August 2018",new OrderTextList(R.drawable.image,R.drawable.image2,R.drawable.image3,R.drawable.image2,R.drawable.image3)));
        mainlist.add(new OrderDisplayModel("16.08.2015","552616515","Rs.1535","Yout Order willbe Deliverd by 26 August 2018",new OrderTextList(R.drawable.image,R.drawable.image2,R.drawable.image3,R.drawable.image2,R.drawable.image3)));
        mainlist.add(new OrderDisplayModel("16.08.2015","552616515","Rs.1535","Yout Order willbe Deliverd by 26 August 2018",new OrderTextList(R.drawable.image,R.drawable.image2,R.drawable.image3,R.drawable.image2,R.drawable.image3)));
        mainlist.add(new OrderDisplayModel("16.08.2015","552616515","Rs.1535","Yout Order willbe Deliverd by 26 August 2018",new OrderTextList(R.drawable.image,R.drawable.image2,R.drawable.image3,R.drawable.image2,R.drawable.image3)));
        mainlist.add(new OrderDisplayModel("16.08.2015","552616515","Rs.1535","Yout Order willbe Deliverd by 26 August 2018",new OrderTextList(R.drawable.image,R.drawable.image2,R.drawable.image3,R.drawable.image2,R.drawable.image3)));
        mainlist.add(new OrderDisplayModel("16.08.2015","552616515","Rs.1535","Yout Order willbe Deliverd by 26 August 2018",new OrderTextList(R.drawable.image,R.drawable.image2,R.drawable.image3,R.drawable.image2,R.drawable.image3)));
        /*mainlist.add(new OrderDisplayModel("16.08.2015","552616515","Rs.1535","Yout Order willbe Deliverd by 26 August 2018", new OrderTextList("sdiefbiuwfnwjkefnwn")));
        mainlist.add(new OrderDisplayModel("16.08.2015","552616515","Rs.1535","Yout Order willbe Deliverd by 26 August 2018", new OrderTextList("sdiefbiuwfnwjkefnwn")));
        mainlist.add(new OrderDisplayModel("16.08.2015","552616515","Rs.1535","Yout Order willbe Deliverd by 26 August 2018", new OrderTextList("sdiefbiuwfnwjkefnwn")));
        mainlist.add(new OrderDisplayModel("16.08.2015","552616515","Rs.1535","Yout Order willbe Deliverd by 26 August 2018", new OrderTextList("sdiefbiuwfnwjkefnwn")));
        mainlist.add(new OrderDisplayModel("16.08.2015","552616515","Rs.1535","Yout Order willbe Deliverd by 26 August 2018", new OrderTextList("sdiefbiuwfnwjkefnwn")));

*/
        OrderDisplayAdapter listmain = new OrderDisplayAdapter(getActivity(),mainlist);
        recyclerView_main.setAdapter(listmain);


        return view;


    }
}
