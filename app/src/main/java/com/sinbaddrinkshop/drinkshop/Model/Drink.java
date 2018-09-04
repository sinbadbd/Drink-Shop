package com.sinbaddrinkshop.drinkshop.Model;

public class Drink {
    private int id;
    private String name;
    private String link;
    private double price;
    private int menu_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public Drink(int id, String name, String link, double price, int menu_id) {


        this.id = id;
        this.name = name;
        this.link = link;
        this.price = price;
        this.menu_id = menu_id;
    }
}
