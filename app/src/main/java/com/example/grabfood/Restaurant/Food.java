package com.example.grabfood.Restaurant;

import android.graphics.Bitmap;

public class Food {
    String name;
    String price;
    String count;
    Bitmap bitmap;

    public Food(String name, String price,String count, Bitmap bitmap) {
        this.name = name;
        this.price = price;
        this.count=count;
        this.bitmap = bitmap;
    }

    public Food(String name, String price,String count) {
        this.name = name;
        this.price = price;
        this.count=count;
        this.bitmap = null;
    }

    public Food(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Food(String name, String price, Bitmap bitmap) {
        this.name = name;
        this.price = price;
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setBitmap(Bitmap bitmap){this.bitmap = bitmap;}
    public Bitmap getBitmap() {return this.bitmap;}
}
