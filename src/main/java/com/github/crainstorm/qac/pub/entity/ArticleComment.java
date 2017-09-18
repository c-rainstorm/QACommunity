package com.github.crainstorm.qac.pub.entity;

import java.sql.Timestamp;

/**
 * Created by chen on 9/17/17.
 */
public class ArticleComment {
    public int id;
    public int user_id;
    public int article_id;
    public String content;
    public Timestamp datetime;
    public int up;
    public int down;
    public int reply_comment_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
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

    public int getReply_comment_id() {
        return reply_comment_id;
    }

    public void setReply_comment_id(int reply_comment_id) {
        this.reply_comment_id = reply_comment_id;
    }
}
