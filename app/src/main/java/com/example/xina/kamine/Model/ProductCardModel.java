package com.example.xina.kamine.Model;

public class ProductCardModel {
    int product_image;
    String product_name,product_price,product_actual_price,product_discount,product_colortype,product_size,product_quantity,product_delivered;


    public int getProduct_image() {
        return product_image;
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

    public String getProduct_delivered() {
        return product_delivered;
    }

    public String getProduct_colortype() {
        return product_colortype;
    }

    public String getProduct_size() {
        return product_size;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public ProductCardModel(int product_image, String product_name, String product_price, String product_actual_price, String product_discount, String product_colortype, String product_size, String product_quantity, String product_delivered) {
        this.product_image = product_image;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_actual_price = product_actual_price;
        this.product_discount = product_discount;
        this.product_colortype = product_colortype;
        this.product_size = product_size;
        this.product_quantity = product_quantity;
        this.product_delivered = product_delivered;
    }
}
