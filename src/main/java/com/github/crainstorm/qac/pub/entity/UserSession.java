package com.github.crainstorm.qac.pub.entity;

/**
 * Created by chen on 9/21/17.
 */
public class UserSession {
    public boolean userLoginStatus;
    public int id;
    public String name;
    public String avatar;

    public boolean isUserLoginStatus() {
        return userLoginStatus;
    }

    public void setUserLoginStatus(boolean userLoginStatus) {
        this.userLoginStatus = userLoginStatus;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userLoginStatus=" + userLoginStatus +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
