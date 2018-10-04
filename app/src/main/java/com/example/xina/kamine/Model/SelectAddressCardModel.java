package com.example.xina.kamine.Model;

import android.widget.RadioButton;

public class SelectAddressCardModel {

    //RadioButton address_select;
    String cust_name,addressline1,addressline2,addressline3,pincode,mobileno;

    public SelectAddressCardModel( String cust_name, String addressline1, String addressline2,
                                  String addressline3, String pincode, String mobileno) {
       // this.address_select = address_select;
        this.cust_name = cust_name;
        this.addressline1 = addressline1;
        this.addressline2 = addressline2;
        this.addressline3 = addressline3;
        this.pincode = pincode;
        this.mobileno = mobileno;
    }

  /*  public RadioButton getAddress_select() {
        return address_select;
    }*/

    public String getCust_name() {
        return cust_name;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public String getAddressline3() {
        return addressline3;
    }

    public String getPincode() {
        return pincode;
    }

    public String getMobileno() {
        return mobileno;
    }
}
