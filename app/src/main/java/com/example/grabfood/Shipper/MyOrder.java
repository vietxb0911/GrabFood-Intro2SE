package com.example.grabfood.Shipper;

import java.io.Serializable;

public class MyOrder implements Serializable {
    public final String name;
    public final int price;
    public final int quantity;

    public MyOrder(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    String getName()
    {
        return this.name;
    }

    String getPrice()
    {
        return (String)"Unit price: " + String.valueOf(this.price);
    }

    String getQuantity()
    {
        return (String)"Quantity: " + String.valueOf(this.quantity);
    }
}
