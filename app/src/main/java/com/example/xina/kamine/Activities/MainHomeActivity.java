package com.example.xina.kamine.Activities;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.xina.kamine.Fragments.AccountFragment;
import com.example.xina.kamine.Fragments.CartFragement;
import com.example.xina.kamine.Fragments.ClosetFragment;
import com.example.xina.kamine.Fragments.CollectionFragment;
import com.example.xina.kamine.Fragments.HomeFragment;
import com.example.xina.kamine.Fragments.LoginScreen;
import com.example.xina.kamine.R;

public class MainHomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar, shupment_toolbar;
    SharedPreferences sp;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_main);
       /* getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        getActionBar().hide();*/
       // View view =getSupportActionBar().getCustomView();
        //getActionBar().hide();
//        getSupportActionBar().hide();
        //toolbar = (Toolbar)findViewById(R.id.toolbaar);
        //getSupportActionBar();
       // setSupportActionBar(toolbar);
       // getSupportActionBar().hide();

        toolbar = findViewById(R.id.toolbar_display_page);
        setSupportActionBar(toolbar);

        shupment_toolbar  = findViewById(R.id.toolbar_shipment);
        setSupportActionBar(shupment_toolbar);

        final FrameLayout frameLayout = (FrameLayout)findViewById(R.id.frag_container);


        FragmentManager manager= getSupportFragmentManager();
        FragmentTransaction transaction  = manager.beginTransaction();
        transaction.replace(R.id.frag_container,new HomeFragment());
        transaction.commit();



        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
       // bottomNavigationView.setVisibility(View.INVISIBLE);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

      /*  CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
*/

        Menu menu = bottomNavigationView.getMenu();
      // menu.findItem(R.id.navigation_account).setIcon(R.drawable.iconred3);
        //menu.findItem(R.id.navigation_collections).setIcon(R.drawable.iconred);
       // menu.findItem(R.id.navigation_home).setIcon(R.drawable.iconkamine);
        //menu.findItem(R.id.navigation_closet).setIcon(R.drawable.iconred);
       // menu.findItem(R.id.navigation_cart).setIcon(R.drawable.sho);
       //BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        sp = getSharedPreferences("pref",MODE_PRIVATE);
        final String name = sp.getString("globalname", null);
        String date = sp.getString("globaldob", null);
        String gender = sp.getString("globalgender", null);
        String lname = sp.getString("globalLname", null);
        final String mobile = sp.getString("globalMobile", null);
        final String email = sp.getString("globalemail", null);
        String ID = sp.getString("globalD", null);



        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_account:
                                if (name != null && mobile != null){
                                    removefragment(new AccountFragment());
                                }
                             // item.setIcon(R.drawable.iconred3);
                                else{
                                    removefragment(new LoginScreen());
                                }
                               // bottomNavigationView.setVisibility(View.GONE);
                                break;
                            case R.id.navigation_collections:
             //                   item.setIcon(R.drawable.iconred3);
                                removefragment(new CollectionFragment());
                                break;
                            case R.id.navigation_home:
                              //  item.setIcon(R.drawable.iconkamine);
                                removefragment(new HomeFragment());
                               // bottomNavigationView.setVisibility(View.VISIBLE);
                                //bottomNavigationView.isShown();
                                break;
                            case R.id.navigation_closet:
                 //               item.setIcon(R.drawable.iconred);
                                removefragment(new ClosetFragment());
                                break;
                            case R.id.navigation_cart:
                                //item.setIcon(R.drawable.iconred3);
                                removefragment(new CartFragement());
                                break;
                        }
                        return true;
                    }
                });

    }

    @SuppressLint("ResourceType")
    private void replaceFragment(android.app.Fragment f) {
        android.app.FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction  = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit);
        //bottomNavigationView.setVisibility(View.VISIBLE);
        transaction.replace(R.id.frag_container,f);
        transaction.addToBackStack(null);
        transaction.commit();

    }
    public void removeBottom(){
        bottomNavigationView.setVisibility(View.GONE);
    }
    public void showBottom(){bottomNavigationView.setVisibility(View.VISIBLE);}


    void removefragment(Fragment f){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction  = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit);
        transaction.replace(R.id.frag_container,f);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
