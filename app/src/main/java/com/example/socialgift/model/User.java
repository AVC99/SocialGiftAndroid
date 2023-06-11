package com.example.socialgift.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

public class User implements Serializable {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String image;

    public User(int id, String name, String lastName, String email, String image) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.image = image;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static ArrayList<User> parseJsonArray(JSONArray jsonArray){
        ArrayList<User> users = new ArrayList<>();
        try{
            for(int i = 0; i < jsonArray.length(); i++){
                int id = jsonArray.getJSONObject(i).getInt("id");
                String name = jsonArray.getJSONObject(i).getString("name");
                String lastName = jsonArray.getJSONObject(i).getString("last_name");
                String email = jsonArray.getJSONObject(i).getString("email");
                String image = jsonArray.getJSONObject(i).getString("image");
                users.add(new User(id, name, lastName, email, image));
            }
        }catch (JSONException e){
            Log.d("JSON", "Error parsing JSON");
        }
        return users;
    }
}
