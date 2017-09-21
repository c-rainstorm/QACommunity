package com.github.crainstorm.qac.pub.entity;

/**
 * Created by chen on 9/21/17.
 */
public class AdminSession {
    public boolean adminLoginStatus;
    public int id;
    public String name;

    public boolean isAdminLoginStatus() {
        return adminLoginStatus;
    }

    public void setAdminLoginStatus(boolean adminLoginStatus) {
        this.adminLoginStatus = adminLoginStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
