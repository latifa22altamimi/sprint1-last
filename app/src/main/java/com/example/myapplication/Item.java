package com.example.myapplication;

import java.util.Arrays;

public class Item {
    private String name;
    private String description;
    private double cost;
    private int id;
    private byte[] image;


    public Item(String name, String description, double cost, int id, byte[] image) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.id = id;
        this.image = image;
    }

    public Item(String name, String description, double cost, byte[] image) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.image = image;
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


}
