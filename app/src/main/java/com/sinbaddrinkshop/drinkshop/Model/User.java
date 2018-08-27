package com.sinbaddrinkshop.drinkshop.Model;

public class User {
    private String phone;
    private String address;
    private String name;
    private String Birthday;
    private String error_msg;

    public User() {
    }

    public User(String phone, String address, String name, String birthday, String error_msg) {
        this.phone = phone;
        this.address = address;
        this.name = name;
        Birthday = birthday;
        this.error_msg = error_msg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
