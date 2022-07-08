package com.gd.bookshopboot.domain;

import java.util.Date;

public class MallOrder {
    int order_id;
    int user_id;
    int book_id;
    String nick_name;
    String tel;
    String address;
    String book_img;
    String book_name;
    double buy_price;
    int buy_num;
    Date buy_time;
    int status;

    public MallOrder() {
    }

    public MallOrder(int order_id, int user_id, int book_id, String nick_name, String tel, String address, String book_img, String book_name, double buy_price, Integer buy_num, Date buy_time, int status) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.nick_name = nick_name;
        this.tel = tel;
        this.address = address;
        this.book_img = book_img;
        this.book_name = book_name;
        this.buy_price = buy_price;
        this.buy_num = buy_num;
        this.buy_time = buy_time;
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBook_img() {
        return book_img;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public double getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(double buy_price) {
        this.buy_price = buy_price;
    }

    public Integer getBuy_num() {
        return buy_num;
    }

    public void setBuy_num(Integer buy_num) {
        this.buy_num = buy_num;
    }

    public Date getBuy_time() {
        return buy_time;
    }

    public void setBuy_time(Date buy_time) {
        this.buy_time = buy_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "mallOrder{" +
                "order_id=" + order_id +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
                ", nick_name='" + nick_name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", book_img='" + book_img + '\'' +
                ", book_name='" + book_name + '\'' +
                ", buy_price=" + buy_price +
                ", buy_num=" + buy_num +
                ", buy_time=" + buy_time +
                ", status=" + status +
                '}';
    }
}
