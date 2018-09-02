package com.example.kproductions.dailypsalm;

import android.graphics.Color;

public class ListData {

    private int imgID;
    private String text;
    private int color;

    public int getThisColor() {
        return color;
    }

    public void setThisColor(int color) {
        this.color = color;
    }

    public ListData(int ic_launcher_background,int color ,String text) {
        this.imgID = ic_launcher_background;
        this.text = text;
        this.color = color;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getTexts() {
        return text;
    }

    public void setTexts(String text) {
        this.text = text;
    }
}
