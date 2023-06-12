package com.example.socialgift.model;

import java.io.Serializable;

public class Message implements Serializable {
    private int id;
    private String content;
    private int senderId;
    private int receiverId;
    private String timeStamp;

    public Message(int id, String content, int senderId, int receiverId, String timeStamp) {
        this.id = id;
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.timeStamp = timeStamp;
    }

    public Message(String content, int senderId, int receiverId, String timeStamp) {
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
