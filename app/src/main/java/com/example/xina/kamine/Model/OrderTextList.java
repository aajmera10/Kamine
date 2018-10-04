package com.example.xina.kamine.Model;

import java.util.ArrayList;

public class OrderTextList {

int img1,img2,img3,img,img4;
    //ArrayList<OrderTextList> list;

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public int getImg2() {
        return img2;
    }

    public void setImg2(int img2) {
        this.img2 = img2;
    }

    public int getImg3() {
        return img3;
    }

    public void setImg3(int img3) {
        this.img3 = img3;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImg4() {
        return img4;
    }

    public void setImg4(int img4) {
        this.img4 = img4;
    }


    public OrderTextList(int img1, int img2, int img3, int img, int img4) {
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img = img;
        this.img4 = img4;
        //this.list = list;

    }
}
