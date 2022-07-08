package com.gd.bookshopboot.domain;

import java.util.Date;

public class Feedback {
    int id;
    String user_name;
    String feedback_info;
    Date feedback_time;
    int feedback_read;
    int user_id;

    public Feedback() {
    }

    public Feedback(int id, String user_name, String feedback_info, Date feedback_time, int feedback_read, int user_id) {
        this.id = id;
        this.user_name = user_name;
        this.feedback_info = feedback_info;
        this.feedback_time = feedback_time;
        this.feedback_read = feedback_read;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getFeedback_info() {
        return feedback_info;
    }

    public void setFeedback_info(String feedback_info) {
        this.feedback_info = feedback_info;
    }

    public Date getFeedback_time() {
        return feedback_time;
    }

    public void setFeedback_time(Date feedback_time) {
        this.feedback_time = feedback_time;
    }

    public int getFeedback_read() {
        return feedback_read;
    }

    public void setFeedback_read(int feedback_read) {
        this.feedback_read = feedback_read;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", feedback_info='" + feedback_info + '\'' +
                ", feedback_time=" + feedback_time +
                ", feedback_read=" + feedback_read +
                ", user_id=" + user_id +
                '}';
    }
}
