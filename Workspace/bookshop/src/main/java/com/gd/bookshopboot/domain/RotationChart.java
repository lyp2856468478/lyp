package com.gd.bookshopboot.domain;

public class RotationChart {
    int id;
    String img;
    String img_name;

    public RotationChart() {
    }

    public RotationChart(int id, String img, String img_name) {
        this.id = id;
        this.img = img;
        this.img_name = img_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    @Override
    public String toString() {
        return "RotationChart{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", img_name='" + img_name + '\'' +
                '}';
    }
}
