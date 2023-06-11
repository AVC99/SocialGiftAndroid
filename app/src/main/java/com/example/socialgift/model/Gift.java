package com.example.socialgift.model;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;

public class Gift implements Serializable {
    private int id;
    private int wishlistId;
    private String productURL;
    private int priority;
    private int booked;

    public Gift(int id, int wishlistId, String productURL, int priority, int booked) {
        this.id = id;
        this.wishlistId = wishlistId;
        this.productURL = productURL;
        this.priority = priority;
        this.booked = booked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public String getProductURL() {
        return productURL;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public static ArrayList<Gift> parseJSONArray(JSONArray array){
        ArrayList<Gift> gifts = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                gifts.add(new Gift(
                        array.getJSONObject(i).getInt("id"),
                        array.getJSONObject(i).getInt("wishlist_id"),
                        array.getJSONObject(i).getString("product_url"),
                        array.getJSONObject(i).getInt("priority"),
                        array.getJSONObject(i).getInt("booked")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gifts;
    }
}
