package com.github.crainstorm.qac.pub.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by chen on 9/21/17.
 */
public class AvatarUpdater {
    public MultipartFile avater;
    public int user_id;

    public MultipartFile getAvater() {
        return avater;
    }

    public void setAvater(MultipartFile avater) {
        this.avater = avater;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
