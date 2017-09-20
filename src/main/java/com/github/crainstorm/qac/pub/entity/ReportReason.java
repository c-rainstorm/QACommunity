package com.github.crainstorm.qac.pub.entity;

/**
 * Created by chen on 9/17/17.
 */
public class ReportReason {
    public int id;
    public String title;
    public String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
