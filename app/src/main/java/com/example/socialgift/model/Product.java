package com.example.socialgift.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String description;
    private String imageURL;
    private Double  price;

    public Product(int id, String name, String description, String imageURL, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Double getPrice() {
        return price;
    }
}
