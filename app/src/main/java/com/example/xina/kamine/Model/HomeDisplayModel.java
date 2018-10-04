package com.example.xina.kamine.Model;

public class HomeDisplayModel {

    private int display_image;
    private String type_of_collection;
    private String new_or_not;

    public HomeDisplayModel(int display_image, String type_of_collection, String new_or_not) {
        this.display_image = display_image;
        this.type_of_collection = type_of_collection;
        this.new_or_not = new_or_not;
    }

    public int getDisplay_image() {
        return display_image;
    }

    public String getType_of_collection() {
        return type_of_collection;
    }

    public String getNew_or_not() {
        return new_or_not;
    }
}
