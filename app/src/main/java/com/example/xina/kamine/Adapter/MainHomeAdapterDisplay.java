package com.example.xina.kamine.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xina.kamine.Activities.MainHomeActivity;
import com.example.xina.kamine.Fragments.ClosetFragment;
import com.example.xina.kamine.Model.HomeDisplayModel;
import com.example.xina.kamine.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainHomeAdapterDisplay extends RecyclerView.Adapter<MainHomeAdapterDisplay.MyViewHolder> {

    private Context mainhomedis;
    private List<HomeDisplayModel> maindis ;

    public MainHomeAdapterDisplay(Context mainhomedis, List<HomeDisplayModel> maindis) {
        this.mainhomedis = mainhomedis;
        this.maindis = maindis;
    }

    @NonNull
    @Override
    public MainHomeAdapterDisplay.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainhomedis);
        View view= inflater.inflate(R.layout.horizontal_home_recview_display_page,null);

               view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = ((AppCompatActivity)mainhomedis).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frag_container, new ClosetFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHomeAdapterDisplay.MyViewHolder holder, int position) {
            HomeDisplayModel adddis = maindis.get(position);
            holder.display_image.setImageDrawable(mainhomedis.getResources().getDrawable(adddis.getDisplay_image()));
            holder.collection_type.setText(adddis.getType_of_collection());
            holder.new_or_not.setText(adddis.getNew_or_not());
            }

    @Override
    public int getItemCount() {
        return maindis.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView display_image;
    TextView collection_type,new_or_not;

    public MyViewHolder(View itemView) {
        super(itemView);
        display_image = itemView.findViewById(R.id.display_pic);
        collection_type= itemView.findViewById(R.id.style_collection);
        new_or_not = itemView.findViewById(R.id.new_or_not);
    }


}

}
