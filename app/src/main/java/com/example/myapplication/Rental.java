package com.example.myapplication;

public class Rental {

    private int id;

    private String user;
    private int itemID;
    private String status;
    private String name;
    private String city;
    private String district ;
    private String phoneNumber;
    private double totalPrice;

    public Rental(int id, String user, int itemID, String status, String name, String city, String district, String phoneNumber, double totalPrice) {
        this.id = id;

        this.user = user;
        this.itemID = itemID;
        this.status = status;
        this.name = name;
        this.city = city;
        this.district = district;
        this.phoneNumber = phoneNumber;
        this.totalPrice = totalPrice;
    }

    public Rental( String user, int itemID, String status, String name, String city, String district, String phoneNumber, double totalPrice) {

        this.user = user;
        this.itemID = itemID;
        this.status = status;
        this.name = name;
        this.city = city;
        this.district = district;
        this.phoneNumber = phoneNumber;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
