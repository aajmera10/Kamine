package com.example.xina.kamine.Fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xina.kamine.Model.EditAddressModel;

import com.example.xina.kamine.R;
import com.example.xina.kamine.Utils.ApiClient;
import com.example.xina.kamine.Utils.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.VIBRATOR_SERVICE;
import static com.facebook.FacebookSdk.getApplicationContext;

public class EditAddressFragment extends Fragment {
    ConstraintLayout save_address,cancel;
    EditText fname,lname,pincode,city,state,country,landmark,phone,add1,add2;
    String firstn,lastn,pin,cit,stat,cou,land,mobile,address,addone,addtwo;
    private ProgressDialog mProgressDialog;
    SharedPreferences sp;
    Animation shake;
@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.edit_address,container,false);
   fname = view.findViewById(R.id.saveadd_fname);
    lname = view.findViewById(R.id.saveadd_lanme);
    pincode = view.findViewById(R.id.saveadd_pincode);
    city = view.findViewById(R.id.saveadd_city);
    state = view.findViewById(R.id.saveadd_state);
    country = view.findViewById(R.id.saveadd_country);
    landmark = view.findViewById(R.id.saveadd_landmark);
    phone = view.findViewById(R.id.saveadd_phone);
    add1 = view.findViewById(R.id.saveadd_addline1);
    final Vibrator vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
    final long[] pattern = {0,400};
    save_address = view.findViewById(R.id.constraintLayout26);
    cancel = view.findViewById(R.id.constraintLayout25);

    sp = getActivity().getSharedPreferences("pref",0);
    String Id = sp.getString("add_id","");

        firstn = sp.getString("edt_firstname","");
        fname.setText(firstn);
        lastn = sp.getString("edt_lastname","");
        lname.setText(lastn);
        pin = sp.getString("edt_pincode","");
        pincode.setText(pin);
        cit =sp.getString("edt_city","");
        city.setText(cit);
        stat = sp.getString("edt_state","");
        state.setText(stat);
        cou = sp.getString("edt_country","");
        country.setText(cou);
        land = sp.getString("edt_landmark","");
        landmark.setText(land);
        mobile = sp.getString("edt_mobile","");
        phone.setText(mobile);
        address = sp.getString("edt_address","");
        add1.setText(address);


    save_address.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(fname.getText().toString().isEmpty()){
                vibrator.vibrate(pattern, -1);
                shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                fname.startAnimation(shake);
                fname.setError("Please Enter the name");
            }else if(lname.getText().toString().isEmpty()){
                vibrator.vibrate(pattern, -1);
                shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                lname.startAnimation(shake);
                lname.setError("Please Enter the Last Name");
            }else if(add1.getText().toString().isEmpty()){
                vibrator.vibrate(pattern, -1);
                shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                add1.startAnimation(shake);
                add1.setError("Please Enter the Last Name");
            }else if(country.getText().toString().isEmpty()){
                vibrator.vibrate(pattern, -1);
                shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                country.startAnimation(shake);
                country.setError("Please Enter the Last Name");
            }
            else if(state.getText().toString().isEmpty()){
                vibrator.vibrate(pattern, -1);
                shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                state.startAnimation(shake);
                state.setError("Please Enter the Last Name");
            }
            else if(city.getText().toString().isEmpty()){
                vibrator.vibrate(pattern, -1);
                shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                city.startAnimation(shake);
                city.setError("Please Enter the Last Name");
            }
            else if(pincode.getText().toString().length()!=6){
                vibrator.vibrate(pattern, -1);
                shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                pincode.startAnimation(shake);
                pincode.setError("Please Enter the Last Name");
            }else if(phone.getText().toString().length()!=10){
                vibrator.vibrate(pattern, -1);
                shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                phone.startAnimation(shake);
                phone.setError("Please Enter the Mobile Number");
            }
            else {
                firstn = fname.getText().toString().trim();
                lastn = lname.getText().toString().trim();
                pin = pincode.getText().toString().trim();
                cit = city.getText().toString().trim();
                stat = state.getText().toString().trim();
                cou = country.getText().toString().trim();
                land = landmark.getText().toString().trim();
                mobile = phone.getText().toString().trim();
                address = add1.getText().toString().trim();

                SharedPreferences sp = getActivity().getSharedPreferences("pref", 0);
                String id = sp.getString("globalD", "");
                String Id = sp.getString("add_id","");
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<EditAddressModel> call = apiInterface.geteditaddress(Id,firstn,lastn,land,address,
                        cit,stat,pin,cou,mobile);
                call.enqueue(new Callback<EditAddressModel>() {
                    @Override
                    public void onResponse(Call<EditAddressModel> call, Response<EditAddressModel> response) {
                        if(response.body().getSuccess()==200){
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<EditAddressModel> call, Throwable t) {

                    }
                });

            }
        }

    });











    return view;
}
}
