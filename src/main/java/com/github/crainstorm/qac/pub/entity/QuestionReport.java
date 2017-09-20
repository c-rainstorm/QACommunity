package com.github.crainstorm.qac.pub.entity;

import java.sql.Timestamp;

/**
 * Created by chen on 9/16/17.
 */
public class QuestionReport {
    public int user_id;
    public int question_id;
    public String title;
    public String content;
    public int report_reason_id;
    public String remarks;
    public boolean status;
    public Timestamp datetime;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
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

    public int getReport_reason_id() {
        return report_reason_id;
    }

    public void setReport_reason_id(int report_reason_id) {
        this.report_reason_id = report_reason_id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
}
