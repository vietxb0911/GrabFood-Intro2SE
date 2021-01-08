package com.example.grabfood.Customer.book_rcv_x1;

public class Book {
    private int resourcId;
    private String name;
    private String price;

    public Book(int resourcId, String name, String price) {
        this.resourcId = resourcId;
        this.name = name;
        this.price = price;
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


}
