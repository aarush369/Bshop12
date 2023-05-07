package com.example.bshop1.models;



import com.google.firebase.Timestamp;

public class UserModels {

    private String name;
    private String userName;
    private Timestamp timestamp;


    public UserModels() {
    }

    public UserModels(String name, String userName, Timestamp timestamp) {
        this.name = name;
        this.userName = userName;
        this.timestamp = timestamp;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}





