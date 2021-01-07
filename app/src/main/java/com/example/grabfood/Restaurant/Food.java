package com.example.grabfood.Restaurant;

public class Food {
    String name;
    String price;
    String count;

    public Food(String name, String price,String count) {
        this.name = name;
        this.price = price;
        this.count=count;
    }
    public Food(String name, String price) {
        this.name = name;
        this.price = price;

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
}
