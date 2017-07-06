package com.example.asus.pokedex;

/**
 * Created by asus on 05/07/2017.
 */

public class Pokemon1 {

    private String name;
    private int number;
    private String url;

    public Pokemon1(String name, String url) {
        this.name = name;
        this.url = url;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String []urlParties = url.split("/");
        return Integer.parseInt(urlParties[urlParties.length - 1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
