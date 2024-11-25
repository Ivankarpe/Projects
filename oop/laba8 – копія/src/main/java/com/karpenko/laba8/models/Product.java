package com.karpenko.laba8.models;

public class Product {

    public String uuid;
    public String name;
    public Double price;

    public String getId() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(String id) {
        this.uuid = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
}
