package com.example.grabfood.Customer;

public class Food {

    private String _name;
    private int _price;

    Food(String name, int price)
    {
        _name = name;
        _price = price;
    }

    public String getName() {
        return _name;
    }

    public int getPrice() {
        return _price;
    }

}
