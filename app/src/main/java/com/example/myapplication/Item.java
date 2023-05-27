package com.example.myapplication;

import java.util.Arrays;

public class Item {
    private String name;
    private String description;
    private double cost;
    private int id;
    private byte[] image;
    private String status;
    private String user;


    public Item(String name, String description, double cost, int id, byte[] image, String Status, String user) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.id = id;
        this.image = image;
        this.status = Status;
        this.user = user;
    }

    public Item(String name, String description, double cost, byte[] image, String status, String user) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.image = image;
        this.status = status;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}

