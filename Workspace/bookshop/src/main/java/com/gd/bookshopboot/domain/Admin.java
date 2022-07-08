package com.gd.bookshopboot.domain;

public class Admin {
    int id;
    String admin_name;
    String admin_password;

    public Admin() {
    }

    public Admin(int id, String admin_name, String admin_password) {
        this.id = id;
        this.admin_name = admin_name;
        this.admin_password = admin_password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    @Override
    public String toString() {
        return "admin{" +
                "id=" + id +
                ", admin_name='" + admin_name + '\'' +
                ", admin_password='" + admin_password + '\'' +
                '}';
    }
}
