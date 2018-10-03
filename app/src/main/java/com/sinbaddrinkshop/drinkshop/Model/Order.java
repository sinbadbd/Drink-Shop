package com.sinbaddrinkshop.drinkshop.Model;

public class Order {

    private long order_id;
    private float order_price;
    private String order_details, order_comments, order_address, email;

    public Order(long order_id, float order_price, String order_details, String order_comments, String order_address, String email) {
        this.order_id = order_id;
        this.order_price = order_price;
        this.order_details = order_details;
        this.order_comments = order_comments;
        this.order_address = order_address;
        this.email = email;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public float getOrder_price() {
        return order_price;
    }

    public void setOrder_price(float order_price) {
        this.order_price = order_price;
    }

    public String getOrder_details() {
        return order_details;
    }

    public void setOrder_details(String order_details) {
        this.order_details = order_details;
    }

    public String getOrder_comments() {
        return order_comments;
    }

    public void setOrder_comments(String order_comments) {
        this.order_comments = order_comments;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
