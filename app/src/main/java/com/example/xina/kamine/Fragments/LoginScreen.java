package com.example.xina.kamine.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xina.kamine.Activities.MainHomeActivity;
import com.example.xina.kamine.MainActivity;
import com.example.xina.kamine.Model.LoginModel;
import com.example.xina.kamine.Model.SocialLoginModel;
import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiInterface;
import com.example.xina.kamine.Utils.ApiClient;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class LoginScreen extends Fragment {
LinearLayout Login;
TextView signup,forgot_password;
RadioButton otp;
String password,user;
ProgressDialog pDialog;
String userName,userLName,userDOB,userGender,userMobile,userID,userEmail,apiemail;
SharedPreferences sp;
TextInputEditText enter_passwd,enter_mail;

//Google Sign in//
    ImageView googbtn;
    private static final int RC_SIGN_IN = 234;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    String gooName,gooId,goolname,googender,goodob,gooemail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_screen,container,false);
        ((MainHomeActivity)getActivity()).removeBottom();
        Login = view.findViewById(R.id.linearLayout2);
        enter_passwd = view.findViewById(R.id.enter_password);
        enter_mail = view.findViewById(R.id.enter_email);
        signup = view.findViewById(R.id.sign_up);
        otp = view.findViewById(R.id.login_otp);
        forgot_password = view.findViewById(R.id.forgot_password);
        googbtn = view.findViewById(R.id.g_login);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==Login)
                {
                    password = enter_passwd.getText().toString().trim();
                    user = enter_mail.getText().toString().trim();
                    new ProgressDialog(getActivity());
                    pDialog.setTitle("Akshat The Designer");
                    pDialog.show();
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<LoginModel> call = apiInterface.getLogin(password,user);
                  //  Call<LoginModel> call = apiInterface.getLogin(password,user);
                    call.enqueue(new Callback<LoginModel>() {
                        @Override
                        public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                            if (response.body().getSuccess()==200)
                            {

                                pDialog.dismiss();
                                Toast.makeText(getActivity(),response.toString(), Toast.LENGTH_SHORT).show();
                                userName  =  response.body().getLoginDetail().getFname();
                                userDOB =    response.body().getLoginDetail().getDob();
                                userGender = response.body().getLoginDetail().getGender();
                                userLName =  response.body().getLoginDetail().getLname();
                                userMobile = response.body().getLoginDetail().getMobile();
                                //userEmail = response.body().getLoginDetail().get
                                userID = response.body().getLoginDetail().getId();
                                apiemail = response.body().getLoginDetail().getEmail();
                                //id=response.body().getLoginDetail().get


                                sp = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
                                SharedPreferences.Editor eg = sp.edit();
                                eg.putString("globalname",userName);
                                eg.putString("globaldob",userDOB);
                                eg.putString("globalgender",userGender);
                                eg.putString("globalLname",userLName);
                                eg.putString("globalMobile",userMobile);
                                eg.putString("globalemail",apiemail);
                                eg.putString("globalD",userID);
                                eg.putBoolean("hasloggedIN",true);
                                //eg.commit();
                                eg.apply();

                                removefragment(new AccountFragment());

                            }
                        }

                        @Override
                        public void onFailure(Call<LoginModel> call, Throwable t) {
                            pDialog.dismiss();
                            Toast.makeText(getActivity(),t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removefragment(new ForgotPassword());
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removefragment(new SignupFragment());
            }
        });
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removefragment(new GetOTP());
            }
        });


        /////Google SignIn///////

        mAuth = FirebaseAuth.getInstance(); //firebase Object Initailization

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)//Google Sign IN Options Object
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), (GoogleApiClient.OnConnectionFailedListener) getContext())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        googbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });



        return view;
    }
         public void onStart() {
             super.onStart();
             if(mAuth.getCurrentUser() != null){

             }
        }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if the requestCode is the Google Sign In code that we defined at starting
        if (requestCode == RC_SIGN_IN) {

            //Getting the GoogleSignIn Task
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                //authenticating with firebase
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){

        goolname = acct.getDisplayName();
        gooId = acct.getId();
        Uri proURL = (acct.getPhotoUrl());


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            new ProgressDialog(getActivity());
                            pDialog.setTitle("Akshat The Designer");
                            pDialog.show();
                            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                          Call<SocialLoginModel> call = apiInterface.getsociallogin(gooName,userMobile,googender,goodob,gooemail);
                          call.enqueue(new Callback<SocialLoginModel>() {
                              @Override
                              public void onResponse(Call<SocialLoginModel> call, Response<SocialLoginModel> response) {
                               if(response.body()("200")){

                               }
                              }

                              @Override
                              public void onFailure(Call<SocialLoginModel> call, Throwable t) {

                              }
                          });






                            Toast.makeText(getContext(), "User Signed In", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }



    private void signIn() {
        //getting the google signin intent
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        //starting the activity for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }





    void removefragment(android.support.v4.app.Fragment f){
        //MainActivity mainActivity = new MainActivity();r
        // String c = String.valueOf(mainActivity.bottomNavigationView.getMenu());
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        // FragmentTransaction fragmentTransaction = getActivity().getSupp().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,R.anim.fragment_slide_right_exit);
        fragmentTransaction.replace(R.id.frag_container, f);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        // getActivity().onBackPressed();
    }
}






//@SuppressLint("ResourceType")
    /*private void replaceFragment(android.app.Fragment f) {
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

    */