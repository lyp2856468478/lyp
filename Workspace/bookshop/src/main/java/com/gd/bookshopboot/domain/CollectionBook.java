package com.gd.bookshopboot.domain;

import java.util.Date;

public class CollectionBook {
    int id;
    int user_id;
    int book_id;
    String book_name;
    Date Collection_time;
    String book_img;

    public CollectionBook() {
    }

    public CollectionBook(int id, int user_id, int book_id, String book_name, Date collection_time, String book_img) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.book_name = book_name;
        Collection_time = collection_time;
        this.book_img = book_img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCollection_time() {
        return Collection_time;
    }

    public void setCollection_time(Date collection_time) {
        Collection_time = collection_time;
    }

    public String getBook_img() {
        return book_img;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }

    @Override
    public String toString() {
        return "collectionbook{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", Collection_time=" + Collection_time +
                ", book_img='" + book_img + '\'' +
                '}';
    }
}
