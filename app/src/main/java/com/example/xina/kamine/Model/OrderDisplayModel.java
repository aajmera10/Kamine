package com.example.xina.kamine.Model;

import java.util.ArrayList;

public class OrderDisplayModel {

    private String order_date;
    private String order_id;
    private String order_total;
    private String sucessful_d_or_not;
    //ArrayList<OrderTextList> list;
    private OrderTextList allItemsInSection;


   /* public OrderDisplayModel(ArrayList<OrderTextList> list) {
    }*/

    public OrderDisplayModel(String order_date, String order_id, String order_total, String sucessful_d_or_not,OrderTextList allItemsInSection) {
        this.order_date = order_date;
        this.order_id = order_id;
        this.order_total = order_total;
        this.sucessful_d_or_not = sucessful_d_or_not;
        this.allItemsInSection = allItemsInSection;
       // this.list = list;

    }



    public String getOrder_date() {
        return order_date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getOrder_total() {
        return order_total;
    }

    public String getSucessful_d_or_not() {
        return sucessful_d_or_not;
    }

    public OrderTextList getAllItemsInSection() {
        return allItemsInSection;
    }

   /* public ArrayList<OrderTextList> getList() {
        return list;
    }*/
    //  RecyclerView item_list;


   /* public OrderDisplayModel(String order_date, String order_id, String order_total, String sucessful_d_or_not) {

        this.order_date = order_date;
        this.order_id = order_id;
        this.order_total = order_total;
        this.sucessful_d_or_not = sucessful_d_or_not;
       // this.item_list = item_list;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_total() {
        return order_total;
    }

    public void setOrder_total(String order_total) {
        this.order_total = order_total;
    }

    public String getSucessful_d_or_not() {
        return sucessful_d_or_not;
    }*/

    /*public String setSucessful_d_or_not(int sucessful_d_or_not) {
        this.sucessful_d_or_not = sucessful_d_or_not;
    }
*/
   /* public RecyclerView getItem_list() {
        return item_list;
    }

    public void setItem_list(RecyclerView item_list) {
        this.item_list = item_list;
    }
*/

}
