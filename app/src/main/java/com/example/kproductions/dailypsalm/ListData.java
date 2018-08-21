package com.example.kproductions.dailypsalm;

public class ListData {

    private int imgID;
    private String text;


    public ListData(int ic_launcher_background, String text) {
        this.imgID = ic_launcher_background;
        this.text = text;
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
