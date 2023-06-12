package com.example.socialgift.model;

import java.io.Serializable;

public class Post implements Serializable {
    private int id;
    private String username;
    private String userImage;
    private String description;
    private String giftName;
    private Double giftPrice;
    private String postImage;
    private String URL;

    public Post(String username, String userImage, String giftName, Double giftPrice, String postImage, String description, int id, String URL) {
        this.username = username;
        this.userImage = userImage;
        this.giftName = giftName;
        this.giftPrice = giftPrice;
        this.postImage = postImage;
        this.description = description;
        this.id = id;
        this.URL = URL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public Double getGiftPrice() {
        return giftPrice;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getDescription() {
        return description;
    }
    public int getId() {
        return id;
    }
    public String getURL() {
        return URL;
    }
}
