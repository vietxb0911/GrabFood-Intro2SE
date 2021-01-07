package com.example.grabfood.Helper;

public class User {
    public String phoneNumber;
    public String password;
    public String address;
    public String email;
    public String fullname;
    public int type;
    public User(){
        //
    }

    public User(String phoneNumber, String password, String address, String email, String fullname, int type){
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.address = address;
        this.type = type;
    }

    public User(String phoneNumber, String password, String address, String email, String fullname){
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.address = address;
        this.type = 0;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public int getType() {
        return type;
    }
}

