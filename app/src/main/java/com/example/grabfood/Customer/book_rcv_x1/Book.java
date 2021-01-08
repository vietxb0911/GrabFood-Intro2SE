package com.example.grabfood.Customer.book_rcv_x1;

import android.util.Log;

import com.example.grabfood.Customer.Food;

import java.util.ArrayList;

public class Book {
    private int resourcId;
    private String name;
    private String price;
    int count;

    public Book(int resourcId, String name, String price) {
        this.resourcId = resourcId;
        this.name = name;
        this.price = price;
        this.count = 0;
    }

    public Book(int resourcId, String name, String price, int count) {
        this.resourcId = resourcId;
        this.name = name;
        this.price = price;
        this.count = count;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getResourcId() {
        return resourcId;
    }

    public void setResourcId(int resourcId) {
        this.resourcId = resourcId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public static ArrayList<Book> mycart =new ArrayList<>();;
    public static void addFoodtoCart(int resourcId, String name, String price, int count){
        mycart.add(new Book( resourcId,  name,  price,  count));
    }
    public static int get_TotalPaymentAmount() {
        int sum = 0;
        for (int i = 0; i < mycart.size(); i++) {
            sum += Integer.valueOf(mycart.get(i).getPrice()).intValue() * mycart.get(i).getCount();
        }
        return sum;
    }

    public static int get_Count() {
        int sum = 0;
        for (int i = 0; i < mycart.size(); i++) {
            sum += mycart.get(i).getCount();
        }
        return sum;
    }
}
