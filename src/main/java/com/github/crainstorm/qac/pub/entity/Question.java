package com.github.crainstorm.qac.pub.entity;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by chen on 9/16/17.
 */
public class Question {
    public int id;
    public int author_id;
    public String title;
    public String content;
    public Timestamp datetime;
    public int browse_num;
    public int up;
    public int down;
    public short status;
    public int status_remarks;
    public int follow_num;
    public int answer_num;
    public ArrayList<Label> labels = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
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

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public int getBrowse_num() {
        return browse_num;
    }

    public void setBrowse_num(int browse_num) {
        this.browse_num = browse_num;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public int getStatus_remarks() {
        return status_remarks;
    }

    public void setStatus_remarks(int status_remarks) {
        this.status_remarks = status_remarks;
    }

    public int getFollow_num() {
        return follow_num;
    }

    public void setFollow_num(int follow_num) {
        this.follow_num = follow_num;
    }

    public int getAnswer_num() {
        return answer_num;
    }

    public void setAnswer_num(int answer_num) {
        this.answer_num = answer_num;
    }

    public ArrayList<Label> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<Label> labels) {
        this.labels = labels;
    }
}
