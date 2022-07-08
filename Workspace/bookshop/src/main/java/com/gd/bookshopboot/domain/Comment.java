package com.gd.bookshopboot.domain;

import java.util.Date;

public class Comment {
    int id;
    int user_id;
    String user_name;
    String comment_info;
    int book_id;
    Date comment_time;
    String book_name;
    String user_img;

    public Comment() {
    }

    public Comment(int id, int user_id, String user_name, String comment_info, int book_id, Date comment_time, String book_name, String user_img) {
        this.id = id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.comment_info = comment_info;
        this.book_id = book_id;
        this.comment_time = comment_time;
        this.book_name = book_name;
        this.user_img = user_img;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment_info() {
        return comment_info;
    }

    public void setComment_info(String comment_info) {
        this.comment_info = comment_info;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", comment_info='" + comment_info + '\'' +
                ", book_id=" + book_id +
                ", comment_time=" + comment_time +
                ", book_name='" + book_name + '\'' +
                ", user_img='" + user_img + '\'' +
                '}';
    }
}
