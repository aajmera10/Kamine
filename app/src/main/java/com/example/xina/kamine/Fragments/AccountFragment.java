package com.example.xina.kamine.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xina.kamine.Activities.MainHomeActivity;
import com.example.xina.kamine.MainActivity;
import com.example.xina.kamine.Model.LogoutModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class AccountFragment extends android.support.v4.app.Fragment implements GoogleApiClient.OnConnectionFailedListener {
ConstraintLayout orders,adddresses,credits;
TextView user_name,user_email,user_mobile_no,logout;
ImageView edit;
String uid;
SharedPreferences preferences;
    final int RC_SIGN_IN = 234;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;

   /* @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    String x = preferences.getString("globalname","");
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ((MainHomeActivity)getActivity()).showBottom();
        View view = inflater.inflate(R.layout.my_account_after_login,container,false);
        credits= view.findViewById(R.id.constraintLayout14);
        adddresses= view.findViewById(R.id.constraintLayout15);
        orders= view.findViewById(R.id.constraintLayout12);
        user_name = view.findViewById(R.id.user_name);
        user_email = view.findViewById(R.id.user_email);
        user_mobile_no = view.findViewById(R.id.user_mobile);
        edit = view.findViewById(R.id.edt_pro);
        preferences = getActivity().getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        //SharedPreferences.Editor eg = preferences.edit();
        String N = preferences.getString("globalname","");
        String M = preferences.getString("globalemail","");
        String T = preferences.getString("globalMobile","");

        user_name.setText(N);
        user_email.setText(M);
        user_mobile_no.setText(T);




        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removefragment(new UpdateProfile());
            }
        });

        credits.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                ((MainHomeActivity)getActivity()).removeBottom();
                /*android.app.FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit);
                fragmentTransaction.replace(R.id.frag_container, new CreditPageFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
                removefragment(new CreditPageFragment());
            }
        });


        orders.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                ((MainHomeActivity)getActivity()).removeBottom();
               /* android.app.FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit);
                fragmentTransaction.replace(R.id.frag_container, new MyOrders());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
               removefragment(new MyOrders());
            }
        });

        adddresses.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                ((MainHomeActivity)getActivity()).removeBottom();
                removefragment(new ShipmentMain());

            }
        });



        mAuth = FirebaseAuth.getInstance(); //firebase Object Initailization

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)//Google Sign IN Options Object
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(),this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //mGoogleApiClient.connect();
        //mGoogleApiClient.disconnect();




        logout = view.findViewById(R.id.Logout_account);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Sure Want To Logout?");
                builder.setMessage("We Will Miss You");
                builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        preferences = getActivity().getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
                        SharedPreferences.Editor eg = preferences.edit();
                        eg.putBoolean("hasloggedIN",false);
                        eg.putString("globalname","");
                        eg.apply();

                        if(preferences.getBoolean("hasgooglelogin",true)){
                            signOut();
                            reFragment(new LoginScreen());
                            mGoogleApiClient.disconnect();


                        }else if(preferences.getBoolean("hasfaceblogin",true)){

                            if (LoginManager.getInstance() != null)
                                    LoginManager.getInstance().logOut();
                                Toast.makeText(getContext(), "logged out", Toast.LENGTH_SHORT).show();
                            reFragment(new LoginScreen());
                            mGoogleApiClient.disconnect();

                        }else {
                            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                            Call<LogoutModel> call = apiInterface.getlogout(uid);
                            call.enqueue(new Callback<LogoutModel>() {
                                @Override
                                public void onResponse(Call<LogoutModel> call, Response<LogoutModel> response) {
                                    preferences = getActivity().getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
                                    SharedPreferences.Editor eg = preferences.edit();
                                    preferences.getString("globalname", "");
                                    preferences.getString("globaldob", "");
                                    preferences.getString("globalgender", "");
                                    preferences.getString("globalLname", "");
                                    preferences.getString("globalMobile", "");
                                    preferences.getString("globalemail", "");
                                    preferences.getString("globalD", "");
                                    uid = preferences.getString("globalD", "");
                                    preferences.edit().clear().apply();

                                    Toast.makeText(getContext(), "Logout Sucessfully", Toast.LENGTH_SHORT).show();
                                    reFragment(new LoginScreen());
                                    mGoogleApiClient.disconnect();

                                }

                                @Override
                                public void onFailure(Call<LogoutModel> call, Throwable t) {
                                    Toast.makeText(getContext(), "Problem Logging Out", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        dialog.cancel();


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });



        return view;
    }

    public  void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // updateUI(false);
                        SharedPreferences preferences = getActivity().getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
                        SharedPreferences.Editor eg = preferences.edit();

                        preferences.getString("globalname","");
                        preferences.getString("globaldob","");
                        preferences.getString("globalgender","");
                        preferences.getString("globalLname","");
                        preferences.getString("globalMobile","");
                        preferences.getString("globalemail","");
                        preferences.getString("globalD","");
                        preferences.getString("globalD","");
                        preferences.edit().clear().apply();
                        eg.putBoolean("hasgooglelogin",false);
                        eg.apply();
                    }
                });
    }


    void removefragment(android.support.v4.app.Fragment f){
        //MainActivity mainActivity = new MainActivity();r
        // String c = String.valueOf(mainActivity.bottomNavigationView.getMenu());
       FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
       // FragmentTransaction fragmentTransaction = getActivity().getSupp().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit);
        fragmentTransaction.replace(R.id.frag_container, f);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        // getActivity().onBackPressed();
    }
    void reFragment(android.support.v4.app.Fragment f){
        //MainActivity mainActivity = new MainActivity();r
        // String c = String.valueOf(mainActivity.bottomNavigationView.getMenu());
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        // FragmentTransaction fragmentTransaction = getActivity().getSupp().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit);
        fragmentTransaction.replace(R.id.frag_container, f);
        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.commit();
        // getActivity().onBackPressed()
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onPause() {
        super.onPause();
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
    }
}
