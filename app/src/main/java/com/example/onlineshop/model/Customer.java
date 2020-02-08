package com.example.onlineshop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Customer {

    @SerializedName("email")
    private String email;

    @SerializedName("id")
    private int ID;

    @SerializedName("username")
    private String userName;

//    @SerializedName("username")
//    private String password;

    public Customer(String email, String password) {
        this.email = email;

    }
}
