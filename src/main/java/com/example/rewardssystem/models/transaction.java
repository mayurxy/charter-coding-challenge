package com.example.rewardssystem.models;

import java.util.Date;

public class transaction {
    private int userId;
    private String userName;
    private String purchaseDate;
    private int purchaseValue;

    public transaction() {
    }

    public transaction(int userId, String userName, String purchaseDate, int purchaseValue) {
        this.userId = userId;
        this.userName = userName;
        this.purchaseDate = purchaseDate;
        this.purchaseValue = purchaseValue;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(int purchaseValue) {
        this.purchaseValue = purchaseValue;
    }
}
