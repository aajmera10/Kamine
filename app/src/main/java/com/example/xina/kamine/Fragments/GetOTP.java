package com.example.xina.kamine.Fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.xina.kamine.R;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.accountkit.ui.SkinManager;
import com.facebook.accountkit.ui.UIManager;

import static android.app.Activity.RESULT_OK;
import static com.facebook.share.internal.DeviceShareDialogFragment.TAG;

public class GetOTP extends android.support.v4.app.Fragment {
    LinearLayout sendotp;
    public static int APP_REQUEST_CODE = 99;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.mobile_get_otp,container,false);
        sendotp = view.findViewById(R.id.sendotptr);
sendotp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       // phoneLogin(getView());
        stsrtloginpage();
    }
});
       // getCurrentAccount();
        return view;
    }

    private void stsrtloginpage() {
        Intent i = new Intent(getActivity(),AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                LoginType.PHONE,AccountKitActivity.ResponseType.TOKEN);
        i.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,configurationBuilder.build());
        startActivityForResult(i,APP_REQUEST_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == APP_REQUEST_CODE){
            AccountKitLoginResult result =
                    data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if(result.getError() != null){
                Toast.makeText(getActivity(), result.getError().getErrorType().getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
            else if (result.wasCancelled()){

                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
else{
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            removefragment(new HomeFragment());
            }
        }

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

    /*
    private void getCurrentAccount(){
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken != null) {
            //Handle Returning User
            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    // Get Account Kit ID
                    String accountKitId = account.getId();
                    Log.e("Account Kit Id", accountKitId);
                    if(account.getPhoneNumber()!=null) {
                        Log.e("CountryCode", "" + account.getPhoneNumber().getCountryCode());
                        Log.e("PhoneNumber", "" + account.getPhoneNumber().getPhoneNumber());
                        //Get phone number
                        PhoneNumber phoneNumber = account.getPhoneNumber();
                        String phoneNumberString = phoneNumber.toString();
                        //logout.setVisibility(View.VISIBLE);
                        //login.setVisibility(View.GONE);
                        Log.e("NumberString", phoneNumberString);
                    }
                    if(account.getEmail()!=null)
                        Log.e("Email",account.getEmail());
                }

                @Override
                public void onError(final AccountKitError error) {
                    // Handle Error

                }
            });

        } else {
            //Handle new or logged out user

            Toast.makeText(getActivity(),"Logged Out User",Toast.LENGTH_SHORT).show();
        }
    }
*/



/*
    public void phoneLogin(@Nullable View view) {
        final Intent intent = new Intent(getActivity(), AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder = new AccountKitConfiguration.AccountKitConfigurationBuilder(
                LoginType.PHONE,AccountKitActivity.ResponseType.TOKEN); // or .ResponseType.CODE
        UIManager uiManager = new SkinManager(
                LoginType.PHONE,
                SkinManager.Skin.TRANSLUCENT,
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? getResources().getColor(R.color.colorPrimary,null):getResources().getColor(R.color.colorPrimary)),
                R.drawable.background,
                SkinManager.Tint.WHITE,
                0.55
        );
        */
/*If you want default country code*//*

        // configurationBuilder.setDefaultCountryCode("IN");
        configurationBuilder.setUIManager(uiManager);
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }
*/
/*
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE && resultCode == RESULT_OK) {
            getCurrentAccount();
        }
    }
*/

    //@SuppressLint("LongLogTag")
/*
    public void logout(@Nullable View view){
        AccountKit.logOut();
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if(accessToken!=null)
            Log.e(TAG,"Still Logged in...");

        else{
           // logout.setVisibility(View.GONE);
        //login.setVisibility(View.VISIBLE);
    }
*/

}
