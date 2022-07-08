package com.gd.bookshopboot.domain;

public class MallCart {
    int cart_id;
    int user_id;
    int book_id;
    String book_name;
    String book_img;
    double price;
    int num;

    public MallCart() {
    }

    public MallCart(int cart_id, int user_id, int book_id, String book_name, String book_img, double price, int num) {
        this.cart_id = cart_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_img = book_img;
        this.price = price;
        this.num = num;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
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

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_img() {
        return book_img;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "mallCart{" +
                "cart_id=" + cart_id +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", book_img='" + book_img + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }
}
