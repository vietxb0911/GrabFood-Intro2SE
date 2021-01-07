package com.example.grabfood.Restaurant;

public class Order {
    private String ID,status,time;

    public Order(String number, String status, String time) {
        this.ID = number;
        this.status = status;
        this.time = time;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
