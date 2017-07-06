package com.example.asus.pokedex;

/**
 * Created by asus on 07/07/2017.
 */

public class ItemModel {
    int num;
    String name;
    String imageUrl;
    String effect;
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNum() {
        String []urlParties = url.split("/");
        return Integer.parseInt(urlParties[urlParties.length - 1]);
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public ItemModel(String name, String imageUrl,  String url) {
        this.url = url;

        this.name = name;
        this.imageUrl = imageUrl;

    }
}
