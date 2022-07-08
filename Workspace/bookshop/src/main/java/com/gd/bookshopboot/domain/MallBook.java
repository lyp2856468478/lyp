package com.gd.bookshopboot.domain;

import java.util.Date;

//图书
public class MallBook {
    int book_id;
    String book_name;
    String author;
    String book_man;
    double price;
    int category_id;
    String introduction;
    int total;
    String book_img;
    int sales;
    Date add_time;
    int recommend;

    public MallBook() {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook_man() {
        return book_man;
    }

    public void setBook_man(String book_man) {
        this.book_man = book_man;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getBook_img() {
        return book_img;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    @Override
    public String toString() {
        return "mallBook{" +
                "book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", author='" + author + '\'' +
                ", book_man='" + book_man + '\'' +
                ", price=" + price +
                ", category_id=" + category_id +
                ", introduction='" + introduction + '\'' +
                ", total=" + total +
                ", book_img='" + book_img + '\'' +
                ", sales=" + sales +
                ", add_time=" + add_time +
                ", recommend=" + recommend +
                '}';
    }
}
