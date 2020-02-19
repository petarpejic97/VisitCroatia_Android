package com.example.myapplication;

public class User {
    private String key;
    private String name;
    private String number;
    private String email;


    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }



    public User(String name,String number,String email){
        this.email=email;
        this.number=number;
        this.name=name;
    }
}
