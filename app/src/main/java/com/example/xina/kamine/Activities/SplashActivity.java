package com.example.xina.kamine.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.xina.kamine.MainActivity;
import com.example.xina.kamine.R;

public class SplashActivity extends Activity {

    SharedPreferences sp;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        sp = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor eg = sp.edit();
       String c = sp.getString("globalname","");
        /*eg.putString("globalname", userName);
        eg.putString("globaldob", userDOB);
        eg.putString("globalgender", userGender);
        eg.putString("globalLname", userLName);
        eg.putString("globalMobile", userMobile);
        eg.putString("globalemail", apiemail);
        eg.putString("globalD", userID);
        eg.putBoolean("hasloggedIN", true);
        eg.apply();*/
       if (c.isEmpty()){
           eg.putBoolean("hasloggedIN",false);
           eg.putString("globalname", "");
           eg.putString("globaldob", "");
           eg.putString("globalgender", "");
           eg.putString("globalLname", "");
           eg.putString("globalMobile", "");
           eg.putString("globalemail", "");
           eg.putString("globalD", "");
           eg.apply();
       }else {
           eg.putBoolean("hasloggedIN",true);
           eg.apply();
       }

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainHomeActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
