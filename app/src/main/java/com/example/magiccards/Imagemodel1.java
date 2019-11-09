package com.example.magiccards;

import com.google.gson.annotations.Expose;

public class Imagemodel1 {

    @Expose
    private String imageUrl;

    public Imagemodel1() {
    }

    public Imagemodel1(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
