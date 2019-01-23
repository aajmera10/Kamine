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
    ///fragments
    private HomeFragment homeFragment;
    private AccountFragment accountFragment;
    private LoginScreen loginScreen;
    private CollectionFragment collectionFragment;
    private ClosetFragment closetFragment;
    private CartFragement cartFragement;
    Toolbar toolbar, shupment_toolbar;
    SharedPreferences sp;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_main);


        toolbar = findViewById(R.id.toolbar_display_page);
        setSupportActionBar(toolbar);

        shupment_toolbar  = findViewById(R.id.toolbar_shipment);
        setSupportActionBar(shupment_toolbar);




        final FrameLayout frameLayout = findViewById(R.id.frag_container);
        FragmentManager manager= getSupportFragmentManager();
        homeFragment = new HomeFragment();
        FragmentTransaction transaction  = manager.beginTransaction();
        transaction.replace(R.id.frag_container,homeFragment,getString(R.string.tag_fragment_home));
        transaction.commit();



        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

      /*  CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
*/

        Menu menu = bottomNavigationView.getMenu();

        sp = getSharedPreferences("pref",MODE_PRIVATE);
        /*SharedPreferences.Editor eg = sp.edit();
        eg.putBoolean("hasloggedIN",false);
        eg.apply();*/
        final String name = sp.getString("globalname", "");
        String date = sp.getString("globaldob", "");
        String gender = sp.getString("globalgender", "");
        String lname = sp.getString("globalLname", "");
        final String mobile = sp.getString("globalMobile", "");
        final String email = sp.getString("globalemail", "");
        String ID = sp.getString("globalD", "");
       // sp.getBoolean("hasloggedIN",false);



        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_account:
                                if (sp.getBoolean("hasloggedIN",true)){
                                    accountFragment = new AccountFragment();
                                    FragmentManager manager = getSupportFragmentManager();
                                    FragmentTransaction transaction  = manager.beginTransaction();
                                    transaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                                            R.anim.fragment_slide_left_exit,
                                            R.anim.fragment_slide_right_enter,
                                            R.anim.fragment_slide_right_exit);
                                    transaction.replace(R.id.frag_container,accountFragment,getString(R.string.tag_accountFragment));
                                    transaction.addToBackStack(getString(R.string.tag_accountFragment));
                                    transaction.commit();
                                }else{
                                    //removefragment(new LoginScreen());
                                    loginScreen = new LoginScreen();
                                    FragmentManager manager = getSupportFragmentManager();
                                    FragmentTransaction transaction  = manager.beginTransaction();
                                    transaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                                            R.anim.fragment_slide_left_exit,
                                            R.anim.fragment_slide_right_enter,
                                            R.anim.fragment_slide_right_exit);
                                    transaction.replace(R.id.frag_container,loginScreen,getString(R.string.tag_login_screen));
                                    transaction.addToBackStack(getString(R.string.tag_login_screen));
                                    transaction.commit();
                                }
                                break;
                            case R.id.navigation_collections:
             //                   item.setIcon(R.drawable.iconred3);
                                removefragment(new CollectionFragment());
                                collectionFragment = new CollectionFragment();
                                FragmentManager manager = getSupportFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                transaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                                        R.anim.fragment_slide_left_exit,
                                        R.anim.fragment_slide_right_enter,
                                        R.anim.fragment_slide_right_exit);



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
