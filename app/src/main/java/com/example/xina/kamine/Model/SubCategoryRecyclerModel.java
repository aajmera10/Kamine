package com.example.xina.kamine.Model;

public class SubCategoryRecyclerModel {

    String Image;
    String Category;

    public SubCategoryRecyclerModel(String image, String category) {
        Image = image;
        Category = category;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
