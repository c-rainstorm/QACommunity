package com.github.crainstorm.qac.pub.entity;

/**
 * Created by chen on 9/17/17.
 */
public class Admin {
    public int id;
    public String password;
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
