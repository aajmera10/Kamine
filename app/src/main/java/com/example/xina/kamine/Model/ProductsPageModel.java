package com.example.xina.kamine.Model;

public class ProductsPageModel {
    String product_name,product_price,product_actual_price,product_discount,product_description;
    int product_main_image,product_delete_button,product_exclusive,product_bag;



    public ProductsPageModel(String product_name, String product_price, String product_actual_price, String product_discount, String product_description,
                             int product_main_image){                       //, int product_delete_button, int product_exclusive, int product_bag) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_actual_price = product_actual_price;
        this.product_discount = product_discount;
        this.product_main_image = product_main_image;
        this.product_description=product_description;

       // this.product_delete_button = product_delete_button;
       // this.product_exclusive = product_exclusive;
        //this.product_bag = product_bag;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_actual_price() {
        return product_actual_price;
    }

    public String getProduct_discount() {
        return product_discount;
    }

    public int getProduct_main_image() {
        return product_main_image;
    }

    public String getProduct_description() {
        return product_description;
    }


    /*public int getProduct_delete_button() {
        return product_delete_button;
    }

    public int getProduct_exclusive() {
        return product_exclusive;
    }

    public int getProduct_bag() {
        return product_bag;
    }*/

}
