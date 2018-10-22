package com.example.xina.kamine.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.EditText;
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
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
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

public class LoginScreen extends Fragment implements GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks{
LinearLayout Login;
TextView signup,forgot_password;
RadioButton otp;
String password,user;
ProgressDialog pDialog;
String userName,userLName,userDOB,userGender,userMobile,userID,userEmail,apiemail;
SharedPreferences sp;
    private ProgressDialog mProgressDialog;
EditText enter_passwd,enter_mail;

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
              if(enter_passwd.getText().toString().isEmpty()&&enter_mail.getText().toString().isEmpty()){

                  Toast.makeText(getActivity(), "Please Enter The Required Values", Toast.LENGTH_SHORT).show();

              }else {
                  password = enter_passwd.getText().toString().trim();
                  user = enter_mail.getText().toString().trim();
                  //  new ProgressDialog(getActivity());
                  //pDialog.setTitle("Akshat The Designer");
                  // pDialog.show();
                  showProgressDialog();
                  ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                  Call<LoginModel> call = apiInterface.getLogin(password,user);
                  //  Call<LoginModel> call = apiInterface.getLogin(password,user);
                  call.enqueue(new Callback<LoginModel>() {
                      @Override
                      public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                          if (response.body().getSuccess()==200)
                          {

                              //pDialog.dismiss();
                              hideProgressDialog();
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
                          //pDialog.dismiss();
                          hideProgressDialog();
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
                .enableAutoManage(getActivity(),this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //mGoogleApiClient.connect();

        

        googbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });



        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }
    }

    public void onPause() {
        super.onPause();
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();

    }




    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            hideProgressDialog();
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

             gooName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            gooemail = acct.getEmail();
            int idx = gooName.lastIndexOf(' ');
            if (idx == -1)
                throw new IllegalArgumentException("Only a single name: " + gooName);
            userName = gooName.substring(0, idx);
            userLName   = gooName.substring(idx + 1);
            googender="";
            goodob = "";
            userMobile="";

          /*  Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);*/
          showProgressDialog();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<SocialLoginModel>call = apiInterface.getsociallogin(gooName,userMobile,googender,goodob,gooemail);
            call.enqueue(new Callback<SocialLoginModel>() {
                @Override
                public void onResponse(Call<SocialLoginModel> call, Response<SocialLoginModel> response) {
                    hideProgressDialog();
                    Toast.makeText(getActivity(),response.body().getMessage() , Toast.LENGTH_SHORT).show();

                    sp = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor eg = sp.edit();
                    eg.putString("globalname",userName);
                    eg.putString("globaldob",goodob);
                    eg.putString("globalgender",googender);
                    eg.putString("globalLname",userLName);
                    eg.putString("globalMobile",userMobile);
                    eg.putString("globalemail",gooemail);
                    eg.putString("globalD",gooId);
                    eg.putBoolean("hasloggedIN",true);
                    eg.putBoolean("hasgooglelogin",true);
                    eg.commit();
                    eg.apply();
                }

                @Override
                public void onFailure(Call<SocialLoginModel> call, Throwable t) {
                        hideProgressDialog();

                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


            removefragment(new AccountFragment());

        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
            Toast.makeText(getContext(), "sign in failed", Toast.LENGTH_SHORT).show();

        }
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
                        preferences.getBoolean("hasloggedIN",false);
                        preferences.getBoolean("hasgooglelogin",false);
                        preferences.getString("globalD","");
                        eg.clear();
                        eg.apply();
                        eg.commit();
                    }
                });
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(getContext(), "onConnectionFailed:" + connectionResult, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
       // signOut();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

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