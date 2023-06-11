package com.example.socialgift.model;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Wishlist implements Serializable {
    private int id;
    private int userId;
    private String name;
    private String description;
    private String endDate;
    private String creationDate;
    private ArrayList<Gift> gifts;

    public Wishlist(int id, int userId, String name, String description, String endDate, ArrayList<Gift> gifts, String creationDate) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.endDate = endDate;
        this.gifts = gifts;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }

    public static ArrayList<Wishlist> parseJSONArray(JSONArray array){
        ArrayList<Wishlist> wishlists = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                wishlists.add(new Wishlist(
                        array.getJSONObject(i).getInt("id"),
                        array.getJSONObject(i).getInt("user_id"),
                        array.getJSONObject(i).getString("name"),
                        array.getJSONObject(i).getString("description"),
                        array.getJSONObject(i).getString("end_date"),
                        Gift.parseJSONArray(array.getJSONObject(i).getJSONArray("gifts")),
                        array.getJSONObject(i).getString("creation_date")
                ));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return wishlists;
    }
}
