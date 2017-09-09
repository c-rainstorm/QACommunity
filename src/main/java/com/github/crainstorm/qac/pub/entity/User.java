package com.github.crainstorm.qac.pub.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by chen on 9/9/17.
 */
public class User extends UserLogin{
    public int id;
    public String name;
    public Date create_date;
    public boolean sex;
    public String short_intro;
    public String intro;
    public String college;
    public String major;
    public String avatar;
    public int prestige;
    public int browse_num;
    public int status;
    public Timestamp unban_datetime;


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

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getShort_intro() {
        return short_intro;
    }

    public void setShort_intro(String short_intro) {
        this.short_intro = short_intro;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public int getBrowse_num() {
        return browse_num;
    }

    public void setBrowse_num(int browse_num) {
        this.browse_num = browse_num;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getUnban_datetime() {
        return unban_datetime;
    }

    public void setUnban_datetime(Timestamp unban_datetime) {
        this.unban_datetime = unban_datetime;
    }
}
