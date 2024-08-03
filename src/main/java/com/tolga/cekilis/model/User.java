package com.tolga.cekilis.model;

public class User {
    private String username;
    private String password;
    private String jokerName;
    private int jokerSayi;
    private boolean isActivate;

    public User() {
    }

    public User(String username, String password, String jokerName, int jokerSayi, boolean isActivate) {
        this.username = username;
        this.password = password;
        this.jokerName = jokerName;
        this.jokerSayi = jokerSayi;
        this.isActivate = isActivate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJokerName() {
        return jokerName;
    }

    public void setJokerName(String jokerName) {
        this.jokerName = jokerName;
    }

    public int getJokerSayi() {
        return jokerSayi;
    }

    public void setJokerSayi(int jokerSayi) {
        this.jokerSayi = jokerSayi;
    }

    public boolean isActivate() {
        return isActivate;
    }

    public void setActivate(boolean activate) {
        isActivate = activate;
    }
}
